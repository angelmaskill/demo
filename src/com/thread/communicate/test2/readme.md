CyclicBarrier适用场景
在《当我们说线程安全时，到底在说什么》一文中讲过内存屏障，它能保证屏障之前的代码一定在屏障之后的代码之前被执行。CyclicBarrier可以译为循环屏障，也有类似的功能。CyclicBarrier可以在构造时指定需要在屏障前执行await的个数，所有对await的调用都会等待，只到调用await的次数达到预定指，所有等待都会立即被唤醒。

从使用场景上来说，CyclicBarrier是让多个线程互相等待某一事件的发生，然后同时被唤醒。
而上文讲的CountDownLatch是让某一线程等待多个线程的状态，然后该线程被唤醒。


CyclicBarrier主要接口分析
CyclicBarrier提供的关键方法如下:

await() 等待其它参与方的到来（调用await()）。如果当前调用是最后一个调用，则唤醒所有其它的线程的等待并且如果在构造CyclicBarrier时指定了action，当前线程会去执行该action，然后该方法返回该线程调用await的次序（getParties()-1说明该线程是第一个调用await的，0说明该线程是最后一个执行await的），接着该线程继续执行await后的代码；如果该调用不是最后一个调用，则阻塞等待；如果等待过程中，当前线程被中断，则抛出InterruptedException；如果等待过程中，其它等待的线程被中断，或者其它线程等待超时，或者该barrier被reset，或者当前线程在执行barrier构造时注册的action时因为抛出异常而失败，则抛出BrokenBarrierException。

await(long timeout, TimeUnit unit) 与await()唯一的不同点在于设置了等待超时时间，等待超时时会抛出TimeoutException。

reset() 该方法会将该barrier重置为它的初始状态，并使得所有对该barrier的await调用抛出BrokenBarrierException。