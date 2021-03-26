package com.xinnuo.se.util;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeployeeNc {
    private StringBuffer strBuf = null;
    private static StringBuffer fileNameBuf = new StringBuffer();
    static final int buffer = 2048;

    /**
     * @param args
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifiedDateS = "2014-06-10 07:20:12";
        Date modifiedDate = null;
        try {
            modifiedDate = df.parse(modifiedDateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List list = new ArrayList();
        /*
         * String path =
         * "D:\\work\\ExportTabExcel\\WebRoot\\WEB-INF\\classes\\"; String out =
         * "D:\\work\\ExportTabExcel\\out\\";
         */
        String WebRoot = "Web";
        //String path = "E:\\xnh\\nccmiims\\";
        String path = "E:\\xnh\\nccminterface\\";        //String out = "E:/xnh/out/";
        String out = "E:\\xnh\\out\\";
        //String out = "E:/xnh/out/";
        String currentDate = df.format(new Date());
        File f = new File(out + "//" + "filelist_" + ".txt");
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        FileWriter fout = new FileWriter(f);
        BufferedWriter buf = new BufferedWriter(fout);
        /*
         * D:\\work\\ExportTabExcel\\src\\
         * D:\\work\\ExportTabExcel\\WebRoot\\WEB-INF\\classes\\
         * D:\\work\\ExportTabExcel\\Web\\WEB-INF\\classes\\
         */
        String fileName = "";
        String filePath = "";
        String targetPath = "";
        String dest = "";
        List l = new DeployeeNc().getFileList(list, new File(path), modifiedDate);
        for (int i = 0; i < l.size(); i++) {
            File file = (File) l.get(i);
            fileName = file.getName();
            filePath = file.getAbsolutePath();
            if ((fileName.endsWith(".java") || fileName.endsWith(".xml")) && filePath.indexOf("\\src\\") > 0 && filePath.indexOf("\\.svn\\") < 0
                    && filePath.indexOf("\\.class\\") < 0) {
                targetPath = filePath.replace("\\src\\", "\\" + WebRoot + "\\WEB-INF\\classes\\");
                targetPath = targetPath.replace(".java", ".class");
                file = new File(targetPath);
                if (!file.exists()) {
                    targetPath = filePath.replace("\\src\\", "\\" + WebRoot + "\\WEB-INF\\classes\\");
                    targetPath = targetPath.replace(".java", ".class");
                }

            } else if ((filePath.indexOf("\\" + WebRoot + "\\WEB-INF\\classes\\") > 0 || filePath.indexOf("\\" + WebRoot + "\\WEB-INF\\classes\\") > 0)) {
                file = new File(filePath);
                continue;

            } else {
                file = new File(targetPath);
                targetPath = filePath.replace("\\" + WebRoot + "\\", "\\" + WebRoot + "\\");
                if (!file.exists()) {
                    targetPath = filePath.replace("\\" + WebRoot + "\\", "\\" + WebRoot + "\\");
                }
            }
            file = new File(targetPath);
            dest = file.getAbsolutePath().replace(path, out);
            dest = dest.replace("\\" + WebRoot + "\\WEB-INF\\classes\\", "\\WEB-INF\\classes\\");
            dest = dest.replace("\\" + WebRoot + "\\WEB-INF\\classes\\", "\\WEB-INF\\classes\\");
            dest = dest.replace("\\" + WebRoot + "\\", "\\");

            try {
                //fileNameBuf.append(file.getAbsolutePath() + " 修改时间：" + df.format(file.lastModified()) + "\r\n");
                fileNameBuf.append(dest + "\t修改时间：\t" + df.format(file.lastModified()) + "\r\n");
                // System.out.println(dest + "+++11111111+++++++");
                if (file.isFile()) {
                    FileUtils.copyFile(file, new File(dest));
                    System.out.println(dest);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        buf.write(fileNameBuf.toString());
        buf.close();
        fout.close();
        System.out.println(l.size() + "++++++++++");
    }

    public List getFileList(List list, File file, Date modifiedDate) {
        try {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isDirectory() && f.getName().indexOf(".svn") < 0) {
                    getFileList(list, f, modifiedDate);
                } else {
                    Date lastModified = new Date(f.lastModified());
                    if (lastModified.after(modifiedDate)) {
                        list.add(f);
                        //System.out.println(f);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getAllFiles(File dir, Date modifiedDate, String dest, String src) throws Exception {
        strBuf = new StringBuffer();
        searchDirectory(dir, modifiedDate, dest, src);
        save(dest);
        return strBuf.toString();
    }

    public String searchDirectory(File dir, Date modifiedDate, String dest, String src) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        File[] dirs = dir.listFiles();
        File fileout = new File(dest);
        if (!fileout.exists()) {
            fileout.mkdirs();
        }
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i].isDirectory()) {
                searchDirectory(dirs[i], modifiedDate, dest, src);
            } else {
                try {
                    // if ((df.format(new
                    // Date(dirs[i].lastModified())).equals(df.format(df.parse(modifiedDate)))))
                    // {
                    Date lastModified = new Date(dirs[i].lastModified());

                    if (lastModified.after(modifiedDate)) {
                        File file = new File(dirs[i].toString());
                        FileInputStream fileIn = new FileInputStream(file);
                        String destDir = file.getAbsolutePath().substring(src.length(), file.getAbsolutePath().lastIndexOf("\\"));
                        File temp = new File(dest + destDir);
                        if (!temp.exists()) {
                            temp.mkdirs();
                        }
                        //System.out.println(src.length() + "===========" + dest.length());
                        //System.out.println(dirs[i].toString() + "===========" + fileout.getAbsolutePath() + "===========" + destDir);
                        fileIn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return strBuf.toString();
    }

    public void save(String dest) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());
            File f = new File(dest + "//" + "filelist_" + currentDate + ".txt");
            FileWriter fout = new FileWriter(f);
            BufferedWriter buf = new BufferedWriter(fout);
            buf.write(fileNameBuf.toString());
            buf.close();
            fout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
