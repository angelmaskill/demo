本文使用ServerSocket编写服务端，Socket编写客户端，或者使用NIO也行。然后使用Wireshark抓包。

所有程序在Eclipse测试通过，不过服务器和客户端要在不同的机器上运行，这是因为Wireshark不抓loopback包的问题。

我是在虚拟机中Run的，并且把虚拟机的网络连接设置成“桥”。

如下所示1-3步为程序，4步为抓包结果。


