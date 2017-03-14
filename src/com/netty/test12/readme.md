Netty版本从3.5.x到4.x版本应该是个巨大的进步，3.x版本将源码都放到一个包里面，通过package来区分，而4.x版本则根据功能分jar包，细分为codec、transport、handler、buffer、common等包，用户使用时可以引用单个jar包，也可以只引用自己需要的jar包，这个是功能内聚。

另外这样实际上是提供了Netty的接口层，用户扩展可以针对某个包进行扩展，添加自己的功能。最有可能被用户单独访问的是那个buffer包，关于buffer的操作有很大的进步，详情会在后面的系列中分析。


在这里吐槽下Netty的版本升级，升级之后包名的前缀都变了，由org.jboss.netty变为io.netty，这个变化对于升级应用特别坑。