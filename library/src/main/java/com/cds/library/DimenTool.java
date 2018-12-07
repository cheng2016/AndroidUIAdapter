package com.cds.library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cdy on 2016/2/3.
 */
public class DimenTool {

    public static void gen() {
        /**
         * 密度因子
         */
        int density;
        /**
         * 屏幕相对宽度
         */
        int width;
        /**
         * 1.根据UI画布大小比例进行换算，假设UI图相对分辨率为1334x750
         * 设备默认缩放因子密度为 density  = 320 / 160 , 即 density = 2
         * 宽度 width = 750
         * 可选项，根据你实际的UI设计图来定义
         */
        density = 2;
        width = 750 / density;

        /**
         * 2.根据UI画布大小比例进行换算，假设UI图相对分辨率为1080x1920
         * 高分率缩放因子密度一般为 density = 80 / 160 , 即 density = 3
         * 宽度 width = 1080
         * 可选项，根据你实际的UI设计图来定义
         */
        density = 3;
        width = 1080 / density;

        gen(width);
    }

    public static void gen(int width) {

        File file = new File("./app/src/main/res/values/dimens.xml");
        BufferedReader reader = null;
//        StringBuilder sw180 = new StringBuilder();
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw320 = new StringBuilder();
        StringBuilder sw360 = new StringBuilder();
        StringBuilder sw380 = new StringBuilder();
        StringBuilder sw410 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();
        StringBuilder sw720 = new StringBuilder();
        StringBuilder sw760 = new StringBuilder();
        StringBuilder sw800 = new StringBuilder();
        StringBuilder sw900 = new StringBuilder();
        StringBuilder sw1080 = new StringBuilder();
        StringBuilder sw1200 = new StringBuilder();
        StringBuilder sw1440 = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {

                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    float num = Float.valueOf(tempString.substring(tempString.indexOf(">") + 1, tempString.indexOf("</dimen>") - 2));


//                    sw180.append(start).append((int) Math.round(num * 180 / width)).append(end).append("\n");
                    sw240.append(start).append(Math.round(num * 240 / width)).append(end).append("\n");
                    sw320.append(start).append(Math.round(num * 320 / width)).append(end).append("\n");
                    sw360.append(start).append(Math.round(num * 360 / width)).append(end).append("\n");

                    sw380.append(start).append(Math.round(num * 384 / width)).append(end).append("\n");
                    sw410.append(start).append(Math.round(num * 410 / width)).append(end).append("\n");

                    sw480.append(start).append(Math.round(num * 480 / width)).append(end).append("\n");

                    sw600.append(start).append(Math.round(num * 600 / width)).append(end).append("\n");
                    sw720.append(start).append(Math.round(num * 720 / width)).append(end).append("\n");

                    sw760.append(start).append(Math.round(num * 768 / width)).append(end).append("\n");
                    sw800.append(start).append(Math.round(num * 800 / width)).append(end).append("\n");
                    sw900.append(start).append(Math.round(num * 900 / width)).append(end).append("\n");

                    sw1080.append(start).append(Math.round(num * 1080 / width)).append(end).append("\n");
                    sw1200.append(start).append(Math.round(num * 1200 / width)).append(end).append("\n");
                    sw1440.append(start).append((int) Math.round(num * 1440 / width)).append(end).append("\n");


                } else {
//                    sw180.append(tempString).append("\n");
                    sw240.append(tempString).append("\n");
                    sw320.append(tempString).append("\n");
                    sw360.append(tempString).append("\n");
                    sw380.append(tempString).append("\n");
                    sw410.append(tempString).append("\n");
                    sw480.append(tempString).append("\n");
                    sw600.append(tempString).append("\n");
                    sw720.append(tempString).append("\n");

                    sw760.append(tempString).append("\n");
                    sw800.append(tempString).append("\n");
                    sw900.append(tempString).append("\n");

                    sw1080.append(tempString).append("\n");
                    sw1200.append(tempString).append("\n");
                    sw1440.append(tempString).append("\n");
                }
                line++;
            }
            reader.close();
            System.out.println("<!--  sw480 -->");
            System.out.println(sw480);
            System.out.println("<!--  sw600 -->");
            System.out.println(sw600);

            System.out.println("<!--  sw720 -->");
            System.out.println(sw720);

//            String sw180file = "./app/src/main/res/values-sw180dp/dimens.xml";
            String sw240file = "./app/src/main/res/values-sw240dp/dimens.xml";
            String sw320file = "./app/src/main/res/values-sw320dp/dimens.xml";
            String sw360file = "./app/src/main/res/values-sw360dp/dimens.xml";
            String sw380file = "./app/src/main/res/values-sw380dp/dimens.xml";
            String sw410file = "./app/src/main/res/values-sw410dp/dimens.xml";
            String sw480file = "./app/src/main/res/values-sw480dp/dimens.xml";
            String sw600file = "./app/src/main/res/values-sw600dp/dimens.xml";
            String sw720file = "./app/src/main/res/values-sw720dp/dimens.xml";

            String sw760file = "./app/src/main/res/values-sw760dp/dimens.xml";
            String sw800file = "./app/src/main/res/values-sw800dp/dimens.xml";
            String sw900file = "./app/src/main/res/values-sw900dp/dimens.xml";

            String sw1080file = "./app/src/main/res/values-sw1080dp/dimens.xml";
            String sw1200file = "./app/src/main/res/values-sw1200dp/dimens.xml";
            String sw1440file = "./app/src/main/res/values-sw1440dp/dimens.xml";

//            writeFile(sw180file, sw180.toString());
            writeFile(sw240file, sw240.toString());
            writeFile(sw320file, sw320.toString());
            writeFile(sw360file, sw360.toString());
            writeFile(sw380file, sw380.toString());
            writeFile(sw410file, sw410.toString());
            writeFile(sw480file, sw480.toString());
            writeFile(sw600file, sw600.toString());
            writeFile(sw720file, sw720.toString());

            writeFile(sw760file, sw760.toString());
            writeFile(sw800file, sw800.toString());
            writeFile(sw900file, sw900.toString());

            writeFile(sw1080file, sw1080.toString());
            writeFile(sw1200file, sw1200.toString());
            writeFile(sw1440file, sw1440.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static void writeFile(String file, String text) throws IOException {
        CreateFileUtil.createFile(file);
        PrintWriter out = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            out = new PrintWriter(new BufferedWriter(fileWriter));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    /**
     * 生成最基本的尺寸
     */
    private static void genBaseDimensSize() {
        System.out.println("<!--   ******************************Font******************************* -->");
        for (int i = 6; i <= 36; i++) {
            StringBuilder sb = new StringBuilder("<dimen name=\"font_size_");
            sb.append(i).append("\">").append(i).append("sp</dimen>");
            System.out.println(sb.toString());
        }
        System.out.println("<!--   ******************************Widget******************************* -->");
        for (int i = 1; i < 600; i++) {
            StringBuilder sb = new StringBuilder("<dimen name=\"widget_size_");
            if (i > 360) {
                i += 4;
                sb.append(i).append("\">").append(i).append("dp</dimen>");
            } else {
                sb.append(i).append("\">").append(i).append("dp</dimen>");
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        genBaseDimensSize();
        gen();
    }
}
