研究如何同时支持不同的通讯协议

http://blog.csdn.net/u013252773/article/details/22108385


注意事项：
1、该段代码能运行出结果，但是运行的时候会报 io.netty.util.IllegalReferenceCountException: refCnt: 0, decrement: 1 异常，已经解决。日志中的提示信息为：
An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception
说明缺少exceptionCaught方法，在server端最后一个Handler中增加这个方法即可。

2、PersonDecoder和StringDecoder中有一个if判断，是为了判断消息究竟是什么协议。如果是String协议的话，格式是【name:xx;age:xx;sex:xx;】，第一个字母是英文字母n，所以判断协议类型时候是读取二进制流的第一个字符进行判断，当然这种判断方式非常幼稚，以后有机会可以进行改善。