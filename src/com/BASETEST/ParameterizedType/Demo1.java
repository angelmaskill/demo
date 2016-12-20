package com.BASETEST.ParameterizedType;

public class Demo1 {
    public static void main(String[] args){
        Point p = new Point();
        p.setX(10);  // int -> Integer -> Object
        p.setY(20);
        int x = (Integer)p.getX();  // 必须向下转型
        int y = (Integer)p.getY();
        System.out.println("This point is：" + x + ", " + y);
       
        p.setX(25.4);  // double -> Integer -> Object
        p.setY("东京180度");
        /**
         * 向下转型存在着风险，而且编译期间不容易发现，只有在运行期间才会抛出异常，所以要尽量避免使用向下转型
         */
        Object mm = p.getX();
        System.out.println("mm的类型:"+mm.getClass().getName());//运行的时候,能够知道参数的类型
        /**
         * 编译期间,必须强行指定向下转型,有潜在风险.
         */
        //double mmm = p.getX();
        //System.out.println("mm的类型:"+mmm.getClass().getName());
        
        double m = (Double)p.getX();  // 必须向下转型
        double n = (Double)p.getY();  // 运行期间抛出异常
        System.out.println("This point is：" + m + ", " + n);
    }
}
class Point{
    Object x = 0;
    Object y = 0;
    public Object getX() {
        return x;
    }
    public void setX(Object x) {
        this.x = x;
    }
    public Object getY() {
        return y;
    }
    public void setY(Object y) {
        this.y = y;
    }
}