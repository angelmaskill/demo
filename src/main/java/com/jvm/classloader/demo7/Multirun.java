package com.jvm.classloader.demo7;

import java.lang.reflect.Method;


/*
 * 每隔500ms运行一次，不断加载class
 */
class Multirun implements Runnable {
    public void run() {
        try {
            while (true) {
                // 每次都创建出一个新的类加载器
                // class需要放在自己package名字的文件夹下
                String url = System.getProperty("user.dir") + "/target/classes";// "/lib/com.jvm.classloader.demo7/GetPI.jar";
                HowswapCL cl = new HowswapCL(url,
                        new String[]{"com.jvm.classloader.demo7.GetPI"});
                System.out.println("HowswapCL loaded by: " + cl.getClass().getClassLoader());
                Class cls = cl.loadClass("com.jvm.classloader.demo7.GetPI");
                Object foo = cls.newInstance();
                System.out.println("foo loaded by: " + foo.getClass().getClassLoader());

                // 被调用函数的参数
                Method m = foo.getClass().getMethod("Output", new Class[]{});
                m.invoke(foo, new Object[]{});
                Thread.sleep(500);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}