package com.socket.demo7;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOClient {

    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        buffer.put("maxlisa test".getBytes());
        buffer.flip();

        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);

        sc.connect(new InetSocketAddress("192.158.126.136", 52333));
        while (!sc.finishConnect())
            ;

        sc.write(buffer);

        System.out.println("select operate");
        Thread.sleep(10000);
        int sum = selector.select();
        while (selector.select() > 0) {
            Thread.sleep(100);

            Set<SelectionKey> set = selector.selectedKeys();

            for (SelectionKey key : set) {
                int ops = key.readyOps();

                if ((ops & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    sc.read(buffer);
                    buffer.flip();
                }
            }
            selector.selectedKeys().clear();
        }
    }

}
