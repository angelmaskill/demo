# 使用场景
当我们创建线程池并且提交任务失败时，线程池会回调RejectedExecutionHandler接口的rejectedExecution(Runnable task, ThreadPoolExecutor executor)方法来处理线程池处理失败的任务，其中task 是用户提交的任务，而executor是当前执行的任务的线程池。可以通过代码的方式来验证。


1. 任务饱和丢弃:当ThreadPoolExecutor执行任务的时候，如果线程池的线程已经饱和，并且任务队列也已满。那么就会做丢弃处理
2. 线程池关闭丢弃过来的任务


其中ThreadPoolExecutor给出了4种基本策略的实现。分别是:

# 丢弃策略
## CallerRunsPolicy:
在这个策略实现中，任务还是会被执行，但线程池中不会开辟新线程，而是提交任务的线程来负责维护任务。

## AbortPolicy
这个RejectedExecutionHandler类和直接丢弃不同的是，不是默默地处理，而是抛出java.util.concurrent.RejectedExecutionException异常，这个异常是RuntimeException的子类。


## DiscardPolicy
直接丢弃

这个也是实现最简单的类，其中的rejectedExecution()方法是空实现，即什么也不做，那么提交的任务将会被丢弃，而不做任何处理。
这个策略使用的时候要小心，要明确需求。不然不知不觉的任务就丢了。


## DiscardOldestPolicy
和上面的有些类似，也是会丢弃掉一个任务，但是是队列中最早的。
注意，会先判断ThreadPoolExecutor对象是否已经进入SHUTDOWN以后的状态。之后取出队列头的任务并不做任何处理，即丢弃，再重新调用execute()方法提交新任务。


# 参考链接
[ThreadPoolExecutor的任务饱和丢弃策略及源码实现 | 三石·道](http://www.molotang.com/articles/553.html)