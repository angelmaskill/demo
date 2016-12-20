给socket连接添加心跳保活机制.
http://blog.csdn.net/younger_z/article/details/50448527

MINA自带了对心跳协议的支持，可以对心跳做出细致的配置，本文在次基础上实现了server端对client端的心跳检测。
在开始之前先简单介绍下keepAlive的机制：

首先，需要搞清楚TCP keepalive是干什么用的。从名字理解就能够知道，keepalive就是用来检测一个tcp connection是否还连接正常。当一个tcp connection建立好之后，如果双方都不发送数据的话，tcp协议本身是不会发送其它的任何数据的，也就是说，在一个idle的connection上，两个socket之间不产生任何的数据交换。从另一个方面讲，当一个connection建立之后，链接双方可以长时间的不发送任何数据，比如几天，几星期甚至几个月，但该connection仍然存在。


所以，这就可能出现一个问题。举例来说，server和client建立了一个connection，server负责接收client的request。当connection建立好之后，client由于某种原因机器停机了。但server端并不知道，所以server就会一直监听着这个connection，但其实这个connection已经失效了。

keepalive就是为这样的场景准备的。当把一个socket设置成了keepalive，那么这个socket空闲一段时间后，它就会向对方发送数据来确认对方仍然存在。放在上面的例子中，如果client停机了，那么server所发送的keepalive数据就不会有response，这样server就能够确认client完蛋了（至少从表面上看是这样）。


这个例子,客户端是心跳发起方