package com.basetest.ParameterizedType;

public class Demo3 {
    public static void main(String[] args) {
        // 实例化泛型类
        Point3<Integer, Integer> p1 = new Point3<Integer, Integer>();
        p1.setX(10);
        p1.setY(20);
        p1.printPoint(p1.getX(), p1.getY());

        Point3<Double, String> p2 = new Point3<Double, String>();
        p2.setX(25.4);
        p2.setY("东京180度");
        p2.printPoint(p2.getX(), p2.getY());
    }
}

// 定义泛型类
class Point3<T1, T2> {
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

    // 定义泛型方法

    /**
     * 泛型方法与泛型类没有必然的联系，泛型方法有自己的类型参数，在普通类中也可以定义泛型方法。泛型方法 printPoint() 中的类型参数 T1, T2 与泛型类 Point 中的 T1, T2 没有必然的联系，也可以使用其他的标识符代替：
     * 类型参数需要放在修饰符后面、返回值类型前面。
     *
     * @param x
     * @param y
     */
    public <V1, V2> void printPoint(V1 x, V2 y) {
        V1 m = x;
        V2 n = y;
        System.out.println("This point is：" + m + ", " + n);
    }
}