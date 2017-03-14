提供多种解码器用于处理半包，如 
LineBasedFrameDecoder、
DelimiterBasedFrameDecoder、
FixedLengthFrameDecoder、
ProtobufVarint32FrameDecoder、
ByteToMessageDecoder以及
LengthFileldBasedFrameDecoder等等。




# 通过在包尾添加回车换行符（\r\n）来区分整包消息。
〖Netty〗解码器类：〖io.netty.handler.codec.LineBasedFrameDecoder〗 

# 通过特殊字符作为分隔符来区分整包消息。
〖Netty〗解码器类：〖io.netty.handler.codec.DelimiterBasedFrameDecoder〗


# 通过固定长度来区分整包消息。
〖Netty〗解码器类：〖io.netty.handler.codec.FixedLengthFrameDecoder〗


# 通过指定长度来标识整包消息-多个长度.
〖Netty〗解码器类：〖io.netty.handler.codec.LengthFieldBasedFrameDecoder〗

pipeline.addFirst("decoder", new LengthFieldBasedFrameDecoder(100000000,0,4,0,4));  

# 通过〖Protobuf〗解码器来区分整包消息:
〖Netty〗解码器类：〖io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder〗
protobuf是google序列化的工具，主要是把数据序列化成二进制的数据来传输用的。它主要优点如下：

1.性能好，效率高；
2.跨语言（java自带的序列化，不能跨语言）

其实，在netty中使用Protobuf需要注意的是：protobufDecoder仅仅负责编码，并不支持读半包，所以在之前，一定要有读半包的处理器。

有三种方式可以选择：

使用netty提供ProtobufVarint32FrameDecoder
继承netty提供的通用半包处理器 LengthFieldBasedFrameDecoder
继承ByteToMessageDecoder类，自己处理半包



# 参考链接:
http://blog.sina.com.cn/s/blog_a4f42c9e0102vfab.html