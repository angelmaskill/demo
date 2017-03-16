
# 生产者消费者模型

## 版本
* Java 5之前实现同步存取时，可以使用普通的一个集合，然后在使用线程的协作和线程同步可以实现生产者，消费者模式，主要的技术就是用好，wait ,notify,notifyAll,sychronized这些关键字。

* 而在java 5之后，可以使用组阻塞队列来实现，此方式大大简少了代码量，使得多线程编程更加容易，安全方面也有保障。 

## 模型
生产者与消费者模型中，要保证以下几点：
 
* 1 同一时间内只能有一个生产者生产
* 2 同一时间内只能有一个消费者消费
* 3 生产者生产的同时消费者不能消费
* 4 消费者消费的同时生产者不能生产
* 5 共享空间空时消费者不能继续消费
* 6 共享空间满时生产者不能继续生产
 
 
 
# 实现方式
1. 采用synchronized锁以及wait notify方法实现
2. 采用Lock锁以及await和signal方法实现
3. 采用BlockingQueue实现


# 说明
BlockingQueue也是java.util.concurrent下的主要用来控制线程同步的工具。
BlockingQueue有四个具体的实现类,根据不同需求,选择不同的实现类

1. ArrayBlockingQueue：一个由数组支持的有界阻塞队列，规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.其所含的对象是以FIFO(先入先出)顺序排序的。
2. LinkedBlockingQueue：大小不定的BlockingQueue,若其构造函数带一个规定大小的参数,生成的BlockingQueue有大小限制,若不带大小参数,所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定.其所含的对象是以FIFO(先入先出)顺序排序的。
LinkedBlockingQueue 可以指定容量，也可以不指定，不指定的话，默认最大是Integer.MAX_VALUE,其中主要用到put和take方法，put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。
3. PriorityBlockingQueue：类似于LinkedBlockQueue,但其所含对象的排序不是FIFO,而是依据对象的自然排序顺序或者是构造函数的Comparator决定的顺序。
4. SynchronousQueue：特殊的BlockingQueue,对其的操作必须是放和取交替完成的。

# 参考博文
[用Java实现生产者消费者的几种方法](https://zhuanlan.zhihu.com/p/20300609)



