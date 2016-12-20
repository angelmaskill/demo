用于监控目录的变化,采用事件的机制.

----------------

在Java1.7以前的版本中，如果程序想要监控文件的变化，比较常规的做法是轮询要监控的文件目录，采用启动一条后台线程。这种做法性能较差尤其对于大批量的文件监控。第三方commons-io.jar包有org.apache.commons.io.monitor类利用观察者模式也可用做文件的监控。
1.7版本后，java.nio.file包提供了目录监控的api即 Watch Service API。我们把想要监控的目录注册进watchservice中，你把想要监控的事件告诉service，比如文件创建，删除，修改。当service检测到所关注的事件，系统的注册的线程就是检测到并做出相应处理。
WatchKeys是线程安全的，可以和 java.nio.concurrent包中的类使用也可以放入 thread pool中使用。

基本步骤：
1，为文件系统创建WatchService
 WatchService watcher = FileSystems.getDefault().newWatchService();
2，所有实现了 Watchable 接口的对象才可以被注册进watchService中。Path类实现了Watchable接口，所以我们要监控的文件路径都要获取它的Path对象。
Path path = Paths.get(fileDirectory)
Path类实现了2个register方法。我们使用 register(WatchService, WatchEvent.Kind<?>...)。有三个参数版本的register方法暂时还没有实现。（第三个参数WatchEvent.Modifier直到JDK1.8还没有给出实现）。
可以监听的事件类型有
ENTRY_CREATE – 新文件创建
ENTRY_DELETE – 文件删除
ENTRY_MODIFY – 文件修改
OVERFLOW – 表示当前的事件可能丢失或被忽略掉，通常我们不用注册这个事件。
Path dir = ...;
try {
    WatchKey key = dir.register(watcher,
                           ENTRY_CREATE,
                           ENTRY_DELETE,
                           ENTRY_MODIFY);
} catch (IOException x) {
    System.err.println(x);
}

3，事件处理的过程
首先，获取key,有三种方法获得watch key.
poll – 获取并删除就绪队列中的key。若可用，返回就绪队列中的key。不可用就返回null值。
poll(long, TimeUnit) – 与poll相同，只是等待TimeUnit的时间
take – 获取可用key.没有可用的就wait
然后，利用key.pollEvents()方法获取WatchEvents的list集合，利用kind()方法获取事件的类型，context()方法获取文件名，count()方法获取次数。
最后事件处理完后，我们需要调用key.reset()方法使key重置到ready状态。若reset()方法返回false，说明key已经失效了，循环可以退出了。这一步很重要，若没有调用reset()方法，则当前的key就不再会获取将来发生的事件。

一个watch key在它的生命周期内有以下3种状态。
Ready 表示key已经准备好接受事件。当key建立时，它就处于这个状态。
Signaled 表示有一个或多个事件在排队等待。一旦一个key被signaled，它就不处于ready状态了，知道reset()重置以后。
Invalid 表示当前key已经不可用了。. 造成Invalid的原因可能有下面几个
调用cancel方法明确的使其失效
文件系统目录变成不可进入
watch service 关闭了