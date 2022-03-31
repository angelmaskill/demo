# Netty_Course

## <a target=_blank href="http://www.cnblogs.com/orange1438/p/4994375.html">Netty(一)引题NIOInduction</a>
	介绍Java BIO（同步阻塞IO）、  
	PseudoAsynchronousIO(伪异步IO)、  
	NIO（非阻塞IO）、  
	AIO（异步IO）、
	这四种IO的情况，并对不同IO模型作比较。
	

## <a target=_blank href="http://www.cnblogs.com/orange1438/p/5003080.html">Netty（二）入门</a>
	使用IDEA 14 + Maven工具，用netty5.0写TimeServer的程序（没解决粘包、拆包情况）。
	

## <a target=_blank href="http://www.cnblogs.com/orange1438/p/5009769.html">Netty（三）TCP粘包拆包处理</a>
	重写《Netty（二）入门》中的TimeServer的程序，使用LineBasedFrameDecoder 和 StringDecoder编码器解决“粘包”、“拆包”的情况。

## <a target=_blank href="http://www.cnblogs.com/orange1438/p/5028697.html">Netty（四）分隔符与定长解码器</a>
	使用FixedLengthFrameDecoder和 DelimiterBasedFrameDecoder编码器解决“粘包”、“拆包”的情况。

## <a target=_blank href="http://www.cnblogs.com/orange1438/p/5075318.html">Netty（五）序列化protobuf在netty中的使用</a>
	使用ProtobufDecoder等Protobuf等编码器解决“使用Protobuf序列化”的情况。

## <a target=_blank href="http://www.cnblogs.com/orange1438/p/5148850.html">Netty（六）Udp协议在netty中的使用</a>
    UDP不需要建立链路，所有代码很简单。
    
## 参考链接:    
http://www.cnblogs.com/orange1438/p/5009769.html    
JAVA 拾遗 --Future 模式与 Promise 模式 https://www.cnkirito.moe/future-and-promise/