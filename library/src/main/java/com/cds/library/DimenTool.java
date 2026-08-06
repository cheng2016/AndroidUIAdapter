package com.cds.library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Build-time generator: scales base dimens.xml into values-sw&lt;N&gt;dp buckets.
 * Only dp/dip values are scaled; sp/px and other units are copied as-is.
 */
public final class DimenTool {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /** Default smallest-width buckets (folder name == scale factor). */
    public static final int[] DEFAULT_BUCKETS = {
            240, 320, 360, 380, 410, 480, 600, 720, 760, 800, 900, 1080, 1200, 1440
    };

    private static final int DEFAULT_DESIGN_WIDTH = 360;

    private static final Pattern DIMEN_LINE = Pattern.compile(
            "^(\\s*<dimen\\b[^>]*>)\\s*([-+]?\\d+(?:\\.\\d+)?)\\s*(dp|dip|sp|px|pt|in|mm)\\s*(</dimen>\\s*)$",
            Pattern.CASE_INSENSITIVE);

    private DimenTool() {
    }

    public static void gen() throws IOException {
        gen(DEFAULT_DESIGN_WIDTH, DEFAULT_BUCKETS,
                new File("./app/src/main/res/values/dimens.xml"),
                new File("./app/src/main/res"));
    }

    /**
     * @param designWidth design canvas width in dp (e.g. 1080px @ density 3 → 360)
     * @param buckets     sw bucket sizes; folder values-sw{N}dp uses scale N / designWidth
     * @param input       base values/dimens.xml
     * @param outputResDir app (or module) res/ directory
     */
    public static void gen(int designWidth, int[] buckets, File input, File outputResDir)
            throws IOException {
        if (designWidth <= 0) {
            throw new IllegalArgumentException("designWidth must be > 0");
        }
        if (buckets == null || buckets.length == 0) {
            throw new IllegalArgumentException("buckets must not be empty");
        }
        if (!input.isFile()) {
            throw new IOException("Base dimens not found: " + input.getAbsolutePath());
        }

        Map<Integer, StringBuilder> outputs = new LinkedHashMap<Integer, StringBuilder>();
        for (int bucket : buckets) {
            if (bucket <= 0) {
                throw new IllegalArgumentException("Invalid bucket: " + bucket);
            }
            outputs.put(bucket, new StringBuilder());
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                appendScaledLine(line, designWidth, outputs);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        for (Map.Entry<Integer, StringBuilder> entry : outputs.entrySet()) {
            int bucket = entry.getKey();
            File outFile = new File(outputResDir, "values-sw" + bucket + "dp/dimens.xml");
            writeFile(outFile, entry.getValue().toString());
            System.out.println("Wrote " + outFile.getPath());
        }
    }

    static void appendScaledLine(String line, int designWidth, Map<Integer, StringBuilder> outputs) {
        Matcher matcher = DIMEN_LINE.matcher(line);
        if (!matcher.matches()) {
            for (StringBuilder sb : outputs.values()) {
                sb.append(line).append('\n');
            }
            return;
        }

        String start = matcher.group(1);
        float value = Float.parseFloat(matcher.group(2));
        String unit = matcher.group(3).toLowerCase();
        String end = matcher.group(4);

        for (Map.Entry<Integer, StringBuilder> entry : outputs.entrySet()) {
            int bucket = entry.getKey();
            StringBuilder sb = entry.getValue();
            sb.append(start);
            if (shouldScale(unit)) {
                sb.append(formatNumber(scaleDp(value, bucket, designWidth)));
            } else {
                sb.append(formatNumber(value));
            }
            sb.append(unit).append(end);
            if (!end.endsWith("\n")) {
                sb.append('\n');
            }
        }
    }

    /** Scale a dp value for a given sw bucket. */
    public static int scaleDp(float value, int targetSw, int designWidth) {
        return Math.round(value * targetSw / (float) designWidth);
    }

    /** Only layout dp/dip should follow screen width; sp/px stay fixed. */
    public static boolean shouldScale(String unit) {
        if (unit == null) {
            return false;
        }
        String u = unit.toLowerCase();
        return "dp".equals(u) || "dip".equals(u);
    }

    static String formatNumber(float value) {
        if (value == (long) value) {
            return Long.toString((long) value);
        }
        return Float.toString(value);
    }

    private static void writeFile(File file, String text) throws IOException {
        CreateFileUtil.ensureParentDirs(file);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), UTF_8));
            writer.write(text);
            if (!text.endsWith("\n")) {
                writer.newLine();
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /** Prints a starter catalog of font/widget dimens to stdout. */
    public static void printBaseDimensCatalog() {
        System.out.println("<!--   ******************************Font******************************* -->");
        for (int i = 6; i <= 36; i++) {
            System.out.println("<dimen name=\"font_size_" + i + "\">" + i + "sp</dimen>");
        }
        System.out.println("<!--   ******************************Widget******************************* -->");
        for (int i = 1; i <= 360; i++) {
            System.out.println("<dimen name=\"widget_size_" + i + "\">" + i + "dp</dimen>");
        }
        for (int i = 365; i <= 600; i += 5) {
            System.out.println("<dimen name=\"widget_size_" + i + "\">" + i + "dp</dimen>");
        }
    }

    public static void main(String[] args) throws IOException {
        Options options = Options.parse(args);
        if (options.printBase) {
            printBaseDimensCatalog();
        }
        gen(options.designWidth, options.buckets, options.input, options.outputResDir);
    }

    static final class Options {
        int designWidth = DEFAULT_DESIGN_WIDTH;
        int[] buckets = DEFAULT_BUCKETS.clone();
        File input = new File("./app/src/main/res/values/dimens.xml");
        File outputResDir = new File("./app/src/main/res");
        boolean printBase;

        static Options parse(String[] args) {
            Options options = new Options();
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if ("--design-width".equals(arg) && i + 1 < args.length) {
                    options.designWidth = Integer.parseInt(args[++i]);
                } else if ("--input".equals(arg) && i + 1 < args.length) {
                    options.input = new File(args[++i]);
                } else if ("--output-dir".equals(arg) && i + 1 < args.length) {
                    options.outputResDir = new File(args[++i]);
                } else if ("--buckets".equals(arg) && i + 1 < args.length) {
                    options.buckets = parseBuckets(args[++i]);
                } else if ("--print-base".equals(arg)) {
                    options.printBase = true;
                } else if ("--help".equals(arg) || "-h".equals(arg)) {
                    printUsage();
                    System.exit(0);
                } else {
                    throw new IllegalArgumentException("Unknown argument: " + arg);
                }
            }
            return options;
        }

        private static int[] parseBuckets(String csv) {
            String[] parts = csv.split(",");
            int[] buckets = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                buckets[i] = Integer.parseInt(parts[i].trim());
            }
            return buckets;
        }

        private static void printUsage() {
            System.out.println("Usage: DimenTool [options]");
            System.out.println("  --design-width N   Design canvas width in dp (default 360)");
            System.out.println("  --input PATH       Base dimens.xml path");
            System.out.println("  --output-dir PATH  Module res/ directory");
            System.out.println("  --buckets N,N,...  sw buckets (default "
                    + Arrays.toString(DEFAULT_BUCKETS).replaceAll("[\\[\\] ]", "") + ")");
            System.out.println("  --print-base       Also print starter dimen catalog to stdout");
        }
    }
}
