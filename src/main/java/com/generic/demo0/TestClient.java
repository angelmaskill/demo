package com.generic.demo0;

import com.generic.demo0.generic.GenericFoo;
import com.generic.demo0.obj.ObjectFoo;

@SuppressWarnings("unused")
public class TestClient {

    public static void main(String[] args) {
//		testObj();
        testGeneric();
    }

    private static void testObj() {
        // TODO Auto-generated method stub
        ObjectFoo foo1 = new ObjectFoo();
        ObjectFoo foo2 = new ObjectFoo();

        foo1.setFoo(new Boolean(true));
        // 记得转换参数类型
        Boolean b = (Boolean) foo1.getFoo();

        foo2.setFoo(new Integer(10));
        // 记得转换参数类型
        Integer i = (Integer) foo2.getFoo();

        /**
         * 以下代码会出现转型错误.
         *
         * <pre>
         *  在使用Object 设计泛型程式时,需要注意
         *  所有存入List、Map、Set 容器中的实例都会失去其型态资讯，要从这些容器中取回物件并加以操作的话，就得记住取回的物件是什么型态。
         * </pre>
         */
        ObjectFoo foo3 = new ObjectFoo();
        foo3.setFoo(new Boolean(true));
        String s = (String) foo3.getFoo();
    }

    private static void testGeneric() {
        GenericFoo<Boolean> foo1 = new GenericFoo<Boolean>();
        GenericFoo<Integer> foo2 = new GenericFoo<Integer>();

        foo1.setFoo(new Boolean(true));
        Boolean b = foo1.getFoo(); // 不需要再转换型态
        System.out.println(b);

        foo2.setFoo(new Integer(10));
        Integer i = foo2.getFoo(); // 不需要再转换型态
        System.out.println(i);


        /**
         * 编译器可以帮您作第一层防线，例如下面的程式会被检查出错误：
         */
//		GenericFoo<Boolean> foo1 = new GenericFoo<Boolean>();
//		foo1.setFoo(new Boolean(true));
//		Integer i = foo1.getFoo(); // 傳回的是Boolean型態
    }
}
