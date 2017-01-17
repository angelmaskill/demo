根据自己的需求，制定特定的分割符作为特定的结束标志，这里以“$_”作为分隔符为例，解决TCP粘包问题。注意客户端和服务器发送一个包中的消息需要以“$_”作为结束符。

[demo](http://blog.csdn.net/dfdsggdgg/article/details/51233956)