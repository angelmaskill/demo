package com.netty.test4.ser;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.http.HttpContent;  
import io.netty.handler.codec.http.HttpHeaders;  
import io.netty.handler.codec.http.HttpRequest;  
  



import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  

import com.netty.Util.ByteBufToBytes;
  
  
public class StringDecoder extends ChannelHandlerAdapter {  
    private static Logger   logger  = LoggerFactory.getLogger(StringDecoder.class);  
    private ByteBufToBytes  reader;  
  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        logger.info("StringDecoder : msg's type is " + msg.getClass());  
        if (msg instanceof HttpRequest) {  
            HttpRequest request = (HttpRequest) msg;  
            reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(request));  
        }  
  
        if (msg instanceof HttpContent) {  
            HttpContent content = (HttpContent) msg;  
            reader.reading(content.content());  
  
            if (reader.isEnd()) {  
                byte[] clientMsg = reader.readFull();  
                logger.info("StringDecoder : change httpcontent to string ");  
                ctx.fireChannelRead(new String(clientMsg));  
            }  
        }  
    }  
  
} 