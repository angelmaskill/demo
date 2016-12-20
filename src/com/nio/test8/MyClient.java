package com.nio.test8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyClient {

    /** 
     * @Title: NIO客户端，发生消息 
     * @Description: 启动100个线程给服务端发消息
     * @param @param args    设定文件 
     * @return void    返回类型 
     * @throws 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        for(int i=0;i<100;i++){
            final int idx = i;
            new Thread(new MyRunnable(idx)).start();
        }
    }

}
class MyRunnable implements Runnable{

    private final int idx;
    public MyRunnable(int idx){
        this.idx = idx;
    }
    @Override
    public void run() {
        SocketChannel socketChannel = null;
        try{
            //连接服务器
            socketChannel = SocketChannel.open();
            SocketAddress remoteAddress = new InetSocketAddress("127.0.0.1",10000);
            socketChannel.connect(remoteAddress);
            //发送请求
            RequestObject requestObject = new RequestObject("request_"+idx,"request_"+idx);
            sendData(socketChannel,requestObject);
            //接收响应
            ResponseObject responseObject = receiveData(socketChannel);
            System.out.println("reveive from server:"+responseObject.toString());
            
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }finally{
            try {
                socketChannel.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }
        
    }
    private void sendData(SocketChannel socketChannel,RequestObject requestObject)throws IOException{
        byte[] bytes = ClassUtil.object2Bytes(requestObject);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        socketChannel.write(buffer);
        //结束输出
        socketChannel.socket().shutdownOutput();
    }
    private ResponseObject receiveData(SocketChannel socketChannel)throws IOException{
        ResponseObject responseObject = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            byte[] bytes;
            int size=0;
            while((size=socketChannel.read(buffer))>=0){
                buffer.flip();
                bytes = new byte[size];
                buffer.get(bytes);
                bos.write(bytes);
                buffer.clear();
            }
            bytes = bos.toByteArray();
            responseObject = (ResponseObject) ClassUtil.bytes2Object(bytes);
            //结束读入
            socketChannel.socket().shutdownInput();
        }finally{
            bos.close();
        }
        
        return responseObject;
    }
}