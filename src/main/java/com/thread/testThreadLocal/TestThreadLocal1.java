package com.thread.testThreadLocal;

/**
 * Threadlocal
 * 线程的隔离
 * 数据存储
 * <pre>
 *      1. 每个线程中维护一个map 对象。
 *      2、map对象是由 entry 组成的。entry 是由key value组成的。key 就是一个threadlocal对象的引用。value就是这个threadlocal 变量的值。
 *      3、线程销毁，threadLocalMap 就会被销毁, 因为父对象已经销毁了，子对象就会被GC消灭。
 *      4、threaLocalMap 的长度不会太长，因为代码中 threaLocal 变量的个数相对较少。
 * </pre>
 *
 * <pre>
 * 1. protected 方法设计的目的就是为了让子类进行重载。
 *
 * 为什么会内存泄露？
 * threadLocal内存泄露的根本原因是：ThreadLocalMap的生命周期和Thread一样长，如果ThreaLocal变量用完了，但是Thread 没执行完，此时又没有
 * 手动删除对应的key，就会导致内存泄漏（gc 可能把 ThreadLocal变量回收为null 了，但是 ThreadLocalMap 中的entry （key 对应 ThreadLocal变
 * 量为null, value为我们保存的值）未被移除。
 *
 * 如何避免内存泄漏？
 * 1. 使用完 ThreadLocal，调用其 remove 方法删除对应的Entry.
 * 2. 使用完 ThreadLocal,当前 Thread 也随之结束（Thread中的成员 ThreaLocalMap 自然会被回收)-->此种方法我们无法控制,特别是在使用了线程池的场景下。
 * </pre>
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-6-14 14:49
 * @description：
 * @modified By：
 * @version:
 */
public class TestThreadLocal1 {

}
