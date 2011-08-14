package com.cccs.serverbrowser;

import java.io.*;

/**
 * User: Craig Cook
 * Date: Nov 12, 2008
 * Time: 5:40:04 PM
 */
public class FileManager {

    private String strFiles = "";
    private int cnt = 4 ;

    public FileManager(int cnt) {
        strFiles = "";
        this.cnt = cnt;
    }

    public void check(File dir) {
        strFiles = strFiles + dir.toString() + "</br>";

        //TODO logic to only search folders X deep

        if (cnt >-1) {
            if (dir.isDirectory()) {
                cnt--;
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    check(new File(dir, children[i]));
                }
            }
        }
    }

    public String getFiles() {
        return this.strFiles;
    }

    public boolean delete(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = delete(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public boolean copy(File source, File dest) {
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }

            InputStream in = null;
            OutputStream out = null;

            try {
                in = new FileInputStream(source);
                out = new FileOutputStream(dest);

                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (Exception e) {
            System.out.println("File copy error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean copyDirectory(File srcPath, File dstPath) {

        try {
        if (srcPath.isDirectory()) {

            if (!dstPath.exists()) {
                dstPath.mkdir();
            }

            String files[] = srcPath.list();

            for (int i = 0; i < files.length; i++) {
                copyDirectory(new File(srcPath, files[i]),
                        new File(dstPath, files[i]));
            }

        } else {

            if (!srcPath.exists()) {
                System.out.println("File or directory does not exist.");
                System.exit(0);

            } else {
                InputStream in = new FileInputStream(srcPath);
                OutputStream out = new FileOutputStream(dstPath);
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];

                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();
            }
        }
        } catch (Exception e) {
            System.out.println("Directory copy error: " + e.getMessage());
            return false;
        }
        return true;
    }
}