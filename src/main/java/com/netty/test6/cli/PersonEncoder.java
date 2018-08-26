package com.netty.test6.cli;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.netty.Util.ByteObjConverter;
import com.netty.Util.Person;
  
/**
 * PersonEncoder 把Person对象转换成二进制进行传送
 * @author mayanlu
 *
 */
public class PersonEncoder extends MessageToByteEncoder<Person>  {  
  
    @Override  
    protected void encode(ChannelHandlerContext ctx, Person msg, ByteBuf out) throws Exception {  
        out.writeBytes(ByteObjConverter.objectToByte(msg));  
    }  
}  