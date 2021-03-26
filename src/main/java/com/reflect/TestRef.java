/**
 * @(#)TestRef.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-8-17     Administrator    Created
 **********************************************
 */

package com.reflect;

/*

类的生命周期
在一个类编译完成之后，下一步就需要开始使用类，如果要使用一个类，肯定离不开JVM。在程序执行中JVM通过装载，链接，初始化这3个步骤完成。
类的装载是通过类加载器完成的，加载器将.class文件的二进制文件装入JVM的方法区，并且在堆区创建描述这个类的java.lang.Class对象。用来封装数据。 但是同一个类只会被类装载器装载以前
链接就是把二进制数据组装为可以运行的状态。

 

链接分为校验，准备，解析这3个阶段
校验一般用来确认此二进制文件是否适合当前的JVM（版本），
准备就是为静态成员分配内存空间，。并设置默认值
解析指的是转换常量池中的代码作为直接引用的过程，直到所有的符号引用都可以被运行程序使用（建立完整的对应关系）
完成之后，类型也就完成了初始化，初始化之后类的对象就可以正常使用了，直到一个对象不再使用之后，将被垃圾回收。释放空间。
当没有任何引用指向Class对象时就会被卸载，结束类的生命周期*/

/**
 * 参考链接：
 * http://www.cnblogs.com/rollenholt/archive/2011/09/02/2163758.html
 *
 * @author Administrator
 * @since 2015-8-17
 */

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by IntelliJ IDEA. 
 * File: TestRef.java 
 * User: leizhimin 
 * Date: 2008-1-28 14:48:44 
 */
public class TestRef extends TestCase {

    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Foo foo = new Foo("这个一个Foo对象！");
        Class clazz = foo.getClass();
        System.out.println("类名：" + clazz.getName());
        Method m1 = clazz.getDeclaredMethod("outInfo");
        Method m2 = clazz.getDeclaredMethod("setMsg", String.class);
        Method m3 = clazz.getDeclaredMethod("getMsg");
        m1.invoke(foo);
        m2.invoke(foo, "重新设置msg信息！");
        String msg = (String) m3.invoke(foo);
        System.out.println("return:" + msg);
    }

    /*
     * 三种获取类名的方法：
     */
    public void testForName() throws Exception {
        Class<?> demo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;
        /**
         * @返回与带有给定字符串名的类或接口相关联的 Class 对象。
         * @调用 forName(" X ") 将导致命名为 X 的类被初始化；不是对象被初始化。
         */
        demo1 = Class.forName("com.reflect.Foo");
        demo2 = new Foo().getClass();//返回此 Object 的运行时类
        demo3 = Foo.class;

        System.out.println("类名称   " + demo1.getName());
        System.out.println("类名称   " + demo2.getName());
        System.out.println("类名称   " + demo3.getName());
    }

    /*
     * newInstance 创造对象
     * 通过类名，实例化出来一个类的对象
     */
    public void testNewInstance() throws Exception {
        Class<?> demo1 = null;
        demo1 = Class.forName("com.reflect.Foo");
        Foo foo = (Foo) demo1.newInstance();
        foo.setMsg("测试");
        System.out.println(foo);
    }

    /*
     * cons[0].newInstance()
     * 通过构造函数来初始化对象
     */
    public void testCons() throws Exception {
        System.out.println("=========testCons====begin");
        Class<?> demo1 = null;
        demo1 = Class.forName("com.reflect.Foo");
        // 取得全部的构造函数：
        Constructor<?> cons[] = demo1.getConstructors();
        /*for (int i = 0; i < cons.length; i++) {
            System.out.println("=============begin");
            System.out.println("第"+i+"构造函数全称" + cons[i]);
            System.out.println("构造函数修饰符" + cons[i].getModifiers());
            System.out.println("构造函数名称" + cons[i].getName());
            System.out.println("构造函数注解" + cons[i].getAnnotations());
            
            
        }*/
        for (int i = 0; i < cons.length; i++) {
            Class<?> p[] = cons[i].getParameterTypes();
            System.out.print("构造方法：  ");
            int mo = cons[i].getModifiers();
            System.out.print(Modifier.toString(mo) + " ");
            System.out.print(cons[i].getName());
            System.out.print("(");
            for (int j = 0; j < p.length; ++j) {
                System.out.print(p[j].getName() + " arg" + i);
                if (j < p.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("){}");
        }

        /*
         * 每次运行，构造函数的位置不一样
         */
        Foo f1 = (Foo) cons[0].newInstance();
        Foo f2 = (Foo) cons[1].newInstance("sb");
        System.out.println(f1);
        System.out.println(f2.getMsg());
    }

    /*
     * getInterfaces()
     * 获取接口相关信息
     */
    public void testGetInterface() throws Exception {
        System.out.println("=========testGetInterface====begin");
        Class<?> demo1 = null;
        demo1 = Class.forName("com.reflect.Foo");
        // 取得全部的构造函数：
        Class<?> cla[] = demo1.getInterfaces();
        for (Class<?> class1 : cla) {
            System.out.println("实现的接口" + class1);
        }
    }

    /*
     * getSuperclass 获取父類
     */
    public void testGetFather() throws Exception {
        System.out.println("=========testGetFather====begin");
        Class<?> demo1 = null;
        demo1 = Class.forName("com.reflect.Foo");
        Class<?> cla = demo1.getSuperclass();
        System.out.println("继承的父类为" + cla);
    }

    /*
     * 获取方法：
     */
    public void testGetMethod() throws Exception {
        System.out.println("=========testGetMethod====begin");
        Class<?> demo1 = null;
        demo1 = Class.forName("com.reflect.Foo");
        Method method[] = demo1.getMethods();
        for (int i = 0; i < method.length; ++i) {
            Class<?> returnType = method[i].getReturnType();
            Class<?> para[] = method[i].getParameterTypes();
            int temp = method[i].getModifiers();
            System.out.print(Modifier.toString(temp) + " ");
            System.out.print(returnType.getName() + "  ");
            System.out.print(method[i].getName() + " ");
            System.out.print("(");
            for (int j = 0; j < para.length; ++j) {
                System.out.print(para[j].getName() + " " + "arg" + j);
                if (j < para.length - 1) {
                    System.out.print(",");
                }
            }
            Class<?> exce[] = method[i].getExceptionTypes();
            if (exce.length > 0) {
                System.out.print(") throws ");
                for (int k = 0; k < exce.length; ++k) {
                    System.out.print(exce[k].getName() + " ");
                    if (k < exce.length - 1) {
                        System.out.print(",");
                    }
                }
            } else {
                System.out.print(")");
            }
            System.out.println();
        }
    }

    public void testGetAllInfo() throws Exception {
        System.out.println("=========testGetAllInfo====begin");
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.Foo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }
        System.out.println("===============实现的接口或者父类的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = demo.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + filed1[j].getName() + ";");
        }
    }


    public void testname() throws Exception {
        System.out.println("=====================");
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //调用Person类中的sayChina方法
            Method method = demo.getMethod("sayChina");
            method.invoke(demo.newInstance());
            //调用Person的sayHello方法
            method = demo.getMethod("sayHello", String.class, int.class);
            method.invoke(demo.newInstance(), "Rollen", 20);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test11() throws Exception {
        System.out.println("=================test11===");
        Class<?> demo = null;
        Object obj = null;
        try {
            demo = Class.forName("com.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            obj = demo.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setter(obj, "Sex", "男", String.class);
        getter(obj, "Sex");
    }


    public void testGetAtt() throws Exception {
        System.out.println("===========testGetAtt");
        Class<?> demo = null;
        Object obj = null;

        demo = Class.forName("com.reflect.Person");
        obj = demo.newInstance();

        Field field = demo.getDeclaredField("sex");
        field.setAccessible(true);
        field.set(obj, "男");
        System.out.println(field.get(obj));
    }


    public void test12() throws Exception {
        System.out.println("===========12");
        int[] temp = {1, 2, 3, 4, 5};
        Class<?> demo = temp.getClass().getComponentType();
        System.out.println("数组类型： " + demo.getName());
        System.out.println("数组长度  " + Array.getLength(temp));
        System.out.println("数组的第一个元素: " + Array.get(temp, 0));
        Array.set(temp, 0, 100);
        System.out.println("修改之后数组第一个元素为： " + Array.get(temp, 0));
    }

    public void test13() throws Exception {
        System.out.println("===========13");
        int[] temp = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] newTemp = (int[]) arrayInc(temp, 15);
        print(newTemp);
        System.out.println("=====================");
        String[] atr = {"a", "b", "c"};
        String[] str1 = (String[]) arrayInc(atr, 8);
        print(str1);
    }

    /**其实在java中有三种类类加载器。

     1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
     2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
     3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。*/

    public void test14() throws Exception {
        System.out.println("========14");
        Foo t = new Foo();
        System.out.println("类加载器  " + t.getClass().getClassLoader().getClass().getName());
    }


    /**
     * @param obj
     *            操作的对象
     * @param att
     *            操作的属性
     * */
    public static void getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" + att);
            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obj
     *            操作的对象
     * @param att
     *            操作的属性
     * @param value
     *            设置的值
     * @param type
     *            参数的属性
     * */
    public static void setter(Object obj, String att, Object value,
                              Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + att, type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改数组大小
     * */
    public static Object arrayInc(Object obj, int len) {
        Class<?> arr = obj.getClass().getComponentType();
        Object newArr = Array.newInstance(arr, len);
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);
        return newArr;
    }

    /**
     * 打印
     * */
    public static void print(Object obj) {
        Class<?> c = obj.getClass();
        if (!c.isArray()) {
            return;
        }
        System.out.println("数组长度为： " + Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i) + " ");
        }
    }
}


class Foo implements Swim {
    private String msg;

    static {
        System.out.println("此时类中静态块被初始化！");
    }


    private Foo(int a, String... ms) {
        super();
    }

    public Foo(String msg) {
        this.msg = msg;
    }

    public Foo() {

    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void outInfo() {
        System.out.println("这是测试Java反射的测试类");
    }

    @Override
    public void swimming() {

    }
}

interface Swim {
    void swimming();
}
