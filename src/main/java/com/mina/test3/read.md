1：
* 设置该选项: public void setSoLinger(boolean on, int seconds) throws SocketException;
* 读取该选项：public int getSoLinger() throws SocketException

    SO_LINGER选项用来控制Socket关闭时的行为，默认情况下，执行Socket的close方法，该方法会立即返回，但底层的Socket实际上并不会立即关闭，他会立即延迟一段时间，知道发送完剩余的数据，才会真正的关闭Socket，断开连接。

    setSoLinger（true, 0): 执行该方法，那么执行Socket的close方法，该方法也会立即返回，但底层的Socket也会立即关闭，所有未发送完的剩余数据被丢弃

    setSoLinger(true, 3600): 那么执行Socket的close方法，该方法不会立即返回，而进入阻塞状态，同时，底层的Socket也会尝试发送剩余的数据，只有满足下面的两个条件之一，close方法才会返回：

    （1）：底层的Socket已经发送完所有的剩余数据

    （2）： 尽管底层的Socket还没有发送完所有的剩余数据，但已经阻塞了3600秒，close()方法的阻塞时间超过3600秒，也会返回，剩余未发送的数据被丢弃。