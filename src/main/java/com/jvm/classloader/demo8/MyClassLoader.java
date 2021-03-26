package com.jvm.classloader.demo8;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    private String name;
    private String path = "d:\\";
    private String fileType = ".class";

    public MyClassLoader(String name) {
        super(); // 让AppClassLoader成为该类加载器的父加载器
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent); // 显式指定父加载器
        this.name = name;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        /**
         * 以下代码不会进行双亲委派方式的加载.
         */
//		byte[] bt = loadData(name);
//		return defineClass(name, bt, 0, bt.length);
        /**
         * 以下代码会进行双亲委派方式的加载.
         */
        byte[] classData = loadData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadData(String name) {
        byte[] data = null;
        FileInputStream is = null;
        ByteArrayOutputStream baos = null;

        try {

//			basePath = System.getProperty("user.dir").replace('\\', '/') + "/target/classes";
            String fullpath = (path + "/" + name).replace('.', '/') + fileType;
//			System.out.println("fullpath==: " + fullpath);
            is = new FileInputStream(fullpath);
            baos = new ByteArrayOutputStream();

            int len = -1;
            while (-1 != (len = is.read())) {
                baos.write(len);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
//			e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return name;
    }
}