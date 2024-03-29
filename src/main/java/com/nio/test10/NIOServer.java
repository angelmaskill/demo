package com.nio.test10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private static final int BLOCK_SIZE = 4096;

    private Selector selector;
    private String file = "D:\\Learning\\Java\\HowTomcatWorksApps.zip";
    private ByteBuffer buffer = ByteBuffer.allocate(BLOCK_SIZE);
    private CharsetDecoder charsetDecoder;

    public NIOServer(int port) throws IOException {
        selector = this.getSelector(port);
        Charset charset = Charset.forName("UTF-8");
        charsetDecoder = charset.newDecoder();
    }

    private Selector getSelector(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }

    public void listen() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                System.out.println("keyset size : " + selectionKeys.size());
                while (it.hasNext()) {
                    SelectionKey selectionKey = it.next();
                    it.remove();
                    handleKey(selectionKey);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("Accept");
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int count = channel.read(buffer);
            if (count > 0) {
                buffer.flip();
                CharBuffer charBuffer = charsetDecoder.decode(buffer);

                String clientName = charBuffer.toString();
                System.out.println("Read From Client : " + clientName);

                SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_WRITE);
                selectionKey.attach(new HandleClient(clientName));
            } else {
                channel.close();
            }
            buffer.clear();
        } else if (key.isWritable()) {
            //获取客户端通道
            SocketChannel channel = (SocketChannel) key.channel();
            HandleClient handleClient = (HandleClient) key.attachment();
            ByteBuffer buffer = handleClient.readBlock();
            System.out.println("Write to client : " + handleClient.getClentName());

            //数据从缓冲区,写入客户端通道
            if (buffer != null) {
                channel.write(buffer);
            } else {
                handleClient.close();
                channel.close();
            }
        }
    }

    private class HandleClient {

        private FileChannel fileChannel;
        private ByteBuffer byteBuffer;
        private String clientName;

        public HandleClient(String clientName) throws FileNotFoundException {
            fileChannel = new FileInputStream(file).getChannel();
            byteBuffer = ByteBuffer.allocate(BLOCK_SIZE);
            this.clientName = clientName;
        }

        public ByteBuffer readBlock() {
            try {
                byteBuffer.clear();
                int count = fileChannel.read(byteBuffer);
                byteBuffer.flip();
                if (count < 0) {
                    return null;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return byteBuffer;
        }

        public void close() {
            try {
                fileChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public String getClentName() {
            return clientName;
        }
    }

    public static void main(String[] args) {
        int port = 12345;

        try {
            NIOServer server = new NIOServer(port);
            System.out.println("Listening on : " + port);
            while (true) {
                server.listen();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
