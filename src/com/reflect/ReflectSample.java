/**
 * @(#)ReflectSample.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-8-17
 */
public class ReflectSample {
    public static void main(String[] args) throws Exception {
        classInfo("com.reflect.ReflectSample$1Point");
        newInstanceAndIncokeMethod();
    }

    private static void classInfo(String className) throws ClassNotFoundException {
        Class clazz = Class.forName(className);

        System.out.println("all implemented interfaces");
        Class[] allInterfaces = clazz.getInterfaces();
        for (int i = 0; i < allInterfaces.length; i++) {
            System.out.println(i + "." + allInterfaces[i]);
        }
        System.out.println("all super classes");
        Class superClass = clazz.getSuperclass();
        int i = 0;
        while (superClass != null) {
            System.out.println(++i + "." + superClass);
            superClass = superClass.getSuperclass();
        }
        System.out.println("methods");
        Method[] methods = clazz.getMethods();
        for (int j = 0; j < methods.length; j++) {
            Method m = methods[j];
            System.out.println(j + "." + m.getName());
        }
        System.out.println("getConstructors");
        int k = 0;
        Constructor[] cs = String.class.getConstructors();
        for (Constructor c : cs) {
            System.out.println(++k + "." + c);
        }
    }

    private static void newInstanceAndIncokeMethod() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Point() {
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public double getDistant() {
                return getDistant(0, 0);
            }

            public double getDistant(int x, int y) {
                int x1 = this.x - x;
                int y1 = this.y - y;
                return Math.sqrt(x1 * x1 + y1 * y1);
            }

            public String toString() {
                return "x=" + x + ",y=" + y;
            }
        }

        System.out.println("常规方式:");
        Point ox = new Point(3, 4);
        System.out.println(ox);
        System.out.println("1111result=" + ox.getDistant());
        System.out.println("1111result=" + ox.getDistant(5, 8));

        System.out.println("反射方式:");
        //Class clazz = Point.class;  
        Class clazz = Class.forName("com.reflect.ReflectSample$1Point");
        System.out.println("获取Point类的构造函数对象，使用该对象得到一个Point类的实例");
        //获取代表这个构造函数的对象public Point(int x, int y)  
        Constructor c = clazz.getConstructor(int.class, int.class);
        Object[] args = new Object[] { 3, 4 };
        //Point o = new Point(3,4);  
        Object o = c.newInstance(args);
        System.out.println(o);
        System.out.println("调用上述代码得到的实例的public double getDistant()方法，并执行它");
        //public double getDistant()  
        Method m = clazz.getMethod("getDistant", null);
        //Double result = o.getDistant();  
        Double result = (Double) m.invoke(o, null);
        System.out.println("result=" + result);
        System.out.println("调用上述代码得到的实例的public double getDistant(int x, int y)方法，" + "并执行getDistant(5,8)");
        //public double getDistant(int x, int y)  
        Method m2 = clazz.getMethod("getDistant", new Class[] { int.class, int.class });
        //Double result2 = o.getDistant(5,8);  
        Double result2 = (Double) m2.invoke(o, new Integer[] { 5, 8 });
        System.out.println("result2=" + result2);
    }
}
