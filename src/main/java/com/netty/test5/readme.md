1.如果业务逻辑比较简单，没有阻塞的情况则可以直接在netty的io线程中处理业务逻辑。

2.如果业务逻辑复杂，耗时长，则建议使用自己实现的业务线程池。将复杂的业务逻辑task提交到业务线程池中。

最后提醒一下，使用自己的线程池的时候注意限流，不然容易高并发情况下容易引起内存泄露。线程池提交任务是异步无阻塞的。高并发情况下可能造成大量的请求积压在线程池的队列里，耗完内存。
tomcat也使用了线程池，但是他有限制连接数。所以使用自己线程池的时候要么也限流，要么实现自己线程池，当任务超过一定量的提交任务时阻塞。

[Netty中，耗时的业务逻辑代码应该写在哪？](https://www.zhihu.com/question/35487154/answer/89255483)