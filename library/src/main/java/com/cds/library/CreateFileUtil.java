package com.cds.library;

import java.io.File;
import java.io.IOException;

/**
 * File helpers for the dimens generator. Overwrites are allowed.
 */
public final class CreateFileUtil {

    private CreateFileUtil() {
    }

    /**
     * Ensures the parent directory of {@code file} exists.
     * Does not refuse when the target file already exists (overwrite-friendly).
     */
    public static void ensureParentDirs(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("file must not be null");
        }
        File parent = file.getParentFile();
        if (parent == null || parent.exists()) {
            return;
        }
        if (!parent.mkdirs() && !parent.isDirectory()) {
            throw new IOException("Failed to create directory: " + parent.getAbsolutePath());
        }
    }

    /** @deprecated Use {@link #ensureParentDirs(File)}; kept for callers that expect a boolean. */
    public static boolean createFile(String destFileName) {
        try {
            File file = new File(destFileName);
            if (destFileName.endsWith(File.separator) || destFileName.endsWith("/")
                    || destFileName.endsWith("\\")) {
                System.out.println("目标不能是目录: " + destFileName);
                return false;
            }
            ensureParentDirs(file);
            if (!file.exists() && !file.createNewFile()) {
                System.out.println("创建文件失败: " + destFileName);
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return dir.isDirectory();
        }
        return dir.mkdirs();
    }
}
