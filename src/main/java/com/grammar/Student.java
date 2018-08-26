/**
 * @(#)Student.java
 * 
 * Copyright Oristand.All rights reserved. This software is the XXX system.
 * 
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-12-3     Administrator    Created
 **********************************************
 */

package com.grammar;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2014-12-3
 */
/*
 * 这是因为如果子类要覆盖了父类中定义的方法，那么不能降低其可见性。
 * 正如上面的例子，人可以讲话，但学生作为人的一种却不能讲话，这显然是不合理的。
 * 即如果在父类中定义一个protected方法，那么在子类中可以将其覆盖，并将访问控制属性改为public,反过来则不行。
 * 不过数据域则没有该限制，上例中People类中定义了public String sex;而在子类中定义private String sex;
 * 这是可行的，该属性是各自类的成员变量，不存在覆盖的问题。
 * 另外，该例中还涉及到了覆盖Object类的toString方法，如果不在子类中重写该方法，那么System.out.print(p1);
 * 将会输出类似Test.Student@e2cb55 speak Chinese的一个字符串（假设前面所提到的错误已改正）,这个字符串的信息不是很有用，应该在子类中加以覆盖，使它返回一个代表该对象的易懂的字符串。
 * 覆盖定义的toString()方法既可以写在People类里，也可以写在Student类里，如果两个类中都重写了，最后调用的将是Student类中的toString().
 */
 class Student extends People {
    private String sex;

//    private void speak() {
    public void speak() {
        System.out.println(" speak Chinese");
    }

    public String toString() { // we can override this function both in class
                                // People and class Student
        return "I";
    }
}
