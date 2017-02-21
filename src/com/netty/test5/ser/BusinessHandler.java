package com.netty.test5.ser;

import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
  


import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  

import com.netty.Util.Person;
  
public class BusinessHandler extends ChannelInboundHandlerAdapter {  
    private Logger  logger  = LoggerFactory.getLogger(BusinessHandler.class);  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        Person person = (Person) msg;  
        logger.info("BusinessHandler read msg from client :" + person); 
        // 给客户端回复消息
        ctx.write(person);  
    }  
  
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
    }  
      
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
          
    }  
} 