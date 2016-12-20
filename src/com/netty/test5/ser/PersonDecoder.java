package com.netty.test5.ser;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.netty.Util.ByteBufToBytes;
import com.netty.Util.ByteObjConverter;
  
  
public class PersonDecoder extends ByteToMessageDecoder {  
    @Override  
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {  
        ByteBufToBytes read = new ByteBufToBytes();  
        Object obj = ByteObjConverter.ByteToObject(read.read(in));  
        out.add(obj);  
    }  
  
}  