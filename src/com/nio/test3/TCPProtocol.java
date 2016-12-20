package com.nio.test3;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/** 
* @author  mengwen  E-mail:  meng_wen@126.com
* @date 创建时间：2016年8月29日 上午11:37:50 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public interface TCPProtocol{  
       /**  
        * 接收一个SocketChannel的处理  
        * @param key  
        * @throws IOException  
        */  
       void handleAccept(SelectionKey key) throws IOException;  

       /**  
        * 从一个SocketChannel读取信息的处理  
        * @param key  
        * @throws IOException  
        */  
       void handleRead(SelectionKey key) throws IOException;  

       /**  
        * 向一个SocketChannel写入信息的处理  
        * @param key  
        * @throws IOException  
        */  
       void handleWrite(SelectionKey key) throws IOException;  
     }