package com.basetest.ParameterizedType;

public class Demo2 {
    public static void main(String[] args) {
        // 实例化泛型类
        Point2<Integer, Integer> p1 = new Point2<Integer, Integer>();
        p1.setX(10);
        p1.setY(20);
        int x = p1.getX();
        int y = p1.getY();
        System.out.println("This point is：" + x + ", " + y);

        Point2<Double, String> p2 = new Point2<Double, String>();
        p2.setX(25.4);
        p2.setY("东京180度");
        Object mm = p2.getX();
        System.out.println("mm类型:" + mm.getClass().getName());//java.lang.Double,能够运行时候正确获取到参数的类型
        /**
         * 编译器间,不必强行进行向下转型,避免了(向下转型的潜在的风险)
         */
        double m = p2.getX();
        String n = p2.getY();
        System.out.println("This point is：" + m + ", " + n);
    }
}
// 定义泛型类

/**
 * 在泛型中，不但数据的值可以通过参数传递，数据的类型也可以通过参数传递。T1, T2 只是数据类型的占位符，运行时会被替换为真正的数据类型。
 *
 * @param <T1>
 * @param <T2>
 * @author mayanlu
 */
class Point2<T1, T2> {
    T1 x;
    T2 y;

    public T1 getX() {
        return x;
    }

    public void setX(T1 x) {
        this.x = x;
    }

    public T2 getY() {
        return y;
    }

    public void setY(T2 y) {
        this.y = y;
    }
}