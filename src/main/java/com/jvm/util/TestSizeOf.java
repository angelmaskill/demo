package com.jvm.util;

import java.util.ArrayList;
import java.util.List;

public class TestSizeOf {
    public static void main(String[] args) throws IllegalAccessException {
        TestSizeOf ts = new TestSizeOf();
        List<User> li = ts.genData();

        /**
         * 方法1
         * 1==28976
         1==24
         */
        System.out.println("1==" + MemoryCalculator.deepSizeOf(li));
        System.out.println("1==" + MemoryCalculator.shallowSizeOf(li));

        /**
         * 方法2
         * 2==20976
         */
        System.out.println("2==" + new MemoryCounter().estimate(li));
        /**
         * 方法3
         *  3==60176
         3==24
         */
        System.out.println("3==" + SizeOfObject.fullSizeOf(li));
        System.out.println("3==" + SizeOfObject.sizeOf(li));
        /**
         *  扩展方法4
         *  Total memory: 64487424
         Free memory: 63297336
         Used memory current: 1190088
         */
        ts.getSizeByGC("4==" + li);

    }


    private List<User> genData() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 1000; i++) {
            list.add(new User("路人" + i, i));
        }
        return list;
    }

    private void getSizeByGC(Object ob) {
        System.gc();
        System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
        System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used memory current: " + usedMemory);
        System.out.println(String.format("Current Stat: %s, Used emory: %s", ob.toString(), new MemoryCounter().estimate(ob)));
    }


}

class User {
    String name = "";
    int age = 0;

    public User(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}