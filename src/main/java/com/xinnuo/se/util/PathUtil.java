package com.xinnuo.se.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class PathUtil {

    /**
     * 开关字段。 该字段决定是否将字符串中路径分隔符统一成'/'。默认为true
     */
    private static boolean replace = true;

    /**
     * 将具体系统的分隔符统一替换为'/',并在最后增加'/'
     */
    private static String replaceSeparatorChar(String path) {
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }

        if (replace) {
            path = path.replace(File.separatorChar, '/');
        }

        return path;
    }

    /**
     * 得到类的绝对路径 <br>
     * D:/Workspaces/Eclipse/Inpatient/WebContent/WEB-INF/classes/com/xyh/util/<br>
     * D:/soft/apache-tomcat-7.0.11/webapps/Inpatient/WEB-INF/classes/com/xyh/util/<br>
     */
    public static String getClassPath(Object object) {
        String path = object.getClass().getResource("").getPath();
        path = new File(path).getAbsolutePath();
        return replaceSeparatorChar(path);
    }

    /**
     * 得到类加载器的绝对路径<br>
     * D:/Workspaces/Eclipse/Inpatient/WebContent/WEB-INF/classes/<br>
     * D:/soft/apache-tomcat-7.0.11/webapps/Inpatient/WEB-INF/classes/<br>
     */
    public static String getRootClassPath() {
        URI classpathURI;
        try {
            classpathURI = PathUtil.class.getClassLoader().getResource("").toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("获取类路径失败。", e);
        }
        return replaceSeparatorChar(new File(classpathURI).getAbsolutePath());
    }

    /**
     * 得到类的包路径<br>
     * com/xyh/util<br>
     * com/xyh/util<br>
     */
    public static String getPackagePath(Object object) {
        Package p = object.getClass().getPackage();
        String path = p != null ? p.getName().replaceAll("\\.", "/") : "";
        return replaceSeparatorChar(path);
    }

    /**
     * 获取当前工程的WEB-INF路径
     *
     * @return D:/Workspaces/Eclipse/Inpatient/WebContent/WEB-INF/
     * D:/soft/apache-tomcat-7.0.11/webapps/Inpatient/WEB-INF/
     */
    public static String getWebInfPath() {
        String path = getRootClassPath();
        path = path.substring(0, path.indexOf("WEB-INF") + 8);
        return path;
    }

    /**
     * 获取当前工程路径，即WEB-INF所在目录
     *
     * @return D:/Workspaces/Eclipse/Inpatient/WebContent/
     * D:/soft/apache-tomcat-7.0.11/webapps/Inpatient/
     */
    public static String getWebRootPath() {
        String path = getRootClassPath();
        path = path.substring(0, path.indexOf("WEB-INF/classes"));
        return path;
    }

    /**
     * 得到本硬盘根目录
     *
     * @return D:/ D:/
     */
    public static String getRootPath() {
        return replaceSeparatorChar(new File("/").getAbsolutePath());
    }

    /**
     * 得到项目根目录
     *
     * @return D:/Workspaces/Eclipse/Inpatient/ D:/soft/apache-tomcat-7.0.11/
     */
    public static String getProjectRootPath() {
        String path = new File("").getAbsolutePath();

        if (path.endsWith("bin")) {
            path = path.substring(0, path.indexOf("bin") - 1);
        }

        return replaceSeparatorChar(path);
    }

    public static void main(String[] args) {
        System.out.println("getClassPath()：\t\t" + PathUtil.getClassPath(new PathUtil()));
        System.out.println("getRootClassPath()：\t" + PathUtil.getRootClassPath());
        System.out.println("getPackagePath()：\t" + PathUtil.getPackagePath(new PathUtil()));
        System.out.println("getWebInfPath()：\t" + PathUtil.getWebInfPath());
        System.out.println("getWebRootPath()：\t" + PathUtil.getWebRootPath());
        System.out.println("getRootPath()：\t" + PathUtil.getRootPath());
        System.out.println("getProjectRootPath()：\t" + PathUtil.getProjectRootPath());
        System.out.println("user.dir：\t" + System.getProperty("user.dir"));
    }
}
