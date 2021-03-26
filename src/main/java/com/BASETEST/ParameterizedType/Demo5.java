package com.BASETEST.ParameterizedType;

/**
 * 如果在使用泛型时没有指明数据类型，那么就会擦除泛型类型
 * 因为在使用泛型时没有指明数据类型，为了不出现错误，编译器会将所有数据向上转型为 Object，所以在取出坐标使用时要向下转型，这与本文一开始不使用泛型没什么两样。
 *
 * @author mayanlu
 */
public class Demo5 {
    public static void main(String[] args) {
        Point p = new Point();  // 类型擦除
        p.setX(10);
        p.setY(20.8);
        int x = (Integer) p.getX();  // 向下转型
        double y = (Double) p.getY();
        System.out.println("This point is：" + x + ", " + y);
    }
}

class Point5<T1, T2> {
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