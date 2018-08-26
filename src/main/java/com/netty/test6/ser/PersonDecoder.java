package com.netty.test6.ser;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.netty.Util.ByteBufToBytes;
import com.netty.Util.ByteObjConverter;
  
/**
 * PersonDecoder  把二进制流转换成Person对象
 * @author mayanlu
 *
 */
public class PersonDecoder extends ByteToMessageDecoder {  
    @Override  
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {  
        byte n = "n".getBytes()[0];  
        byte p = in.readByte();  
        in.resetReaderIndex();  
        if (n != p) {  
            // 把读取的起始位置重置  
            ByteBufToBytes reader = new ByteBufToBytes();  
            out.add(ByteObjConverter.ByteToObject(reader.read(in)));  
        } else {  
            // 执行其它的decode  
            ctx.fireChannelRead(in);  
        }  
    }  
}  