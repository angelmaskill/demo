CountDownLatch适用场景
Java多线程编程中经常会碰到这样一种场景——某个线程需要等待一个或多个线程操作结束（或达到某种状态）才开始执行。比如开发一个并发测试工具时，主线程需要等到所有测试线程均执行完成再开始统计总共耗费的时间，此时可以通过CountDownLatch轻松实现。


CountDownLatch主要接口分析

CountDownLatch工作原理相对简单，可以简单看成一个倒计时器，在构造方法中指定初始值，每次调用countDown()方法时讲计数器减1，而await()会等待计数器变为0。CountDownLatch关键接口如下:

countDown() 如果当前计数器的值大于1，则将其减1；若当前值为1，则将其置为0并唤醒所有通过await等待的线程；若当前值为0，则什么也不做直接返回。
await() 等待计数器的值为0，若计数器的值为0则该方法返回；若等待期间该线程被中断，则抛出InterruptedException并清除该线程的中断状态。
await(long timeout, TimeUnit unit) 在指定的时间内等待计数器的值为0，若在指定时间内计数器的值变为0，则该方法返回true；若指定时间内计数器的值仍未变为0，则返回false；若指定时间内计数器的值变为0之前当前线程被中断，则抛出InterruptedException并清除该线程的中断状态。
getCount() 读取当前计数器的值，一般用于调试或者测试。