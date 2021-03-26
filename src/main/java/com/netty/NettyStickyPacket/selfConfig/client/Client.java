package com.netty.NettyStickyPacket.selfConfig.client;

import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 10101);
        // 消息内容
        String message = "hello";
        byte[] bytes = message.getBytes();
        // 构造字节数组，长度为（4+内容长度）
        // 其中4个字节长度字段是int为4个字节
        ByteBuffer buffer = ByteBuffer.allocate(4 + bytes.length);
        // 设置长度字段（仅仅是内容的长度）
        buffer.putInt(bytes.length);
        // 设置内容
        buffer.put(bytes);

        byte[] array = buffer.array();
        for (int i = 0; i < 20; i++) {
            socket.getOutputStream().write(array);
        }
        socket.close();
    }
}