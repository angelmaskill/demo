package com.nio.test8;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
public class MyServer {

    /** 
     * @Title: 使用NIO实现Socket通信服务器端 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param args    设定文件 
     * @return void    返回类型 
     * @throws 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;
        try
        {
            //开启IO多路复用轮询
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//必须配置为非阻塞
            serverSocketChannel.socket().setReuseAddress(true);//设置当连接处于超时状态时，是否允许新的连接使用旧的连接端口
            serverSocketChannel.socket().bind(new InetSocketAddress(10000));//绑定服务器本地地址和端口
            //在多路复用轮询器上注册操作
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server is ready.............");
            while(selector.select()>0){
                //取得就绪操作
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey readyKey = it.next();
                    it.remove();
                    execute((ServerSocketChannel)readyKey.channel());
                }
            }
            System.out.println("server is over.............");
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }finally{
            try {
                selector.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }
        
    }
    /***/
    private static void execute(ServerSocketChannel serverSocketChannel)throws IOException{
        SocketChannel socketChannel = null;
        try{
            //收到客户端请求信息
            socketChannel = serverSocketChannel.accept();
            RequestObject requestObject = receiveData(socketChannel);
            System.out.println("receive from Client:"+requestObject.toString());
            //发生响应信息到客户端
            ResponseObject responseObject = new ResponseObject("response for "+requestObject.getName(),
                    "response for "+requestObject.getValue());
            sendData(socketChannel,responseObject);
        }finally{
            socketChannel.close();
        }
    }
    private static RequestObject receiveData(SocketChannel socketChannel)throws IOException{
        RequestObject requestObject = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try{
            byte[] bytes;
            int size = 0;
            while((size=socketChannel.read(buffer))>=0){
                //反转指针，由写状态到读状态
                buffer.flip();
                bytes = new byte[size];
                buffer.get(bytes);
                bos.write(bytes);
                buffer.clear();
            }
            bytes = bos.toByteArray();
            Object result = ClassUtil.bytes2Object(bytes);
            requestObject = (RequestObject)result;
        }finally{
            bos.close();
        }
        return requestObject;
    }
    private static void sendData(SocketChannel socketChannel,ResponseObject responseObject)throws IOException{
        byte[] bytes = ClassUtil.object2Bytes(responseObject);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        socketChannel.write(buffer);
    }
}
