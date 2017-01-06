package com.netty.test6.cli;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netty.Util.Person;
  
/**
 * ClientInitHandler 向服务端发送Person对象
 * @author mayanlu
 *
 */
public class ClientInitHandler extends ChannelInboundHandlerAdapter {  
    private static Logger   logger  = LoggerFactory.getLogger(ClientInitHandler.class);  
    private Person person;  
    public ClientInitHandler(Person person){  
        this.person = person;  
    }  
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        logger.info("ClientInitHandler.channelActive");  
        ctx.write(person);  
        ctx.flush();  
    }  
} 