package com.mina.test2;

import java.nio.charset.Charset;  
import java.nio.charset.CharsetDecoder;  
import org.apache.mina.core.buffer.IoBuffer;  
import org.apache.mina.core.session.IoSession;  
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;  
import org.apache.mina.filter.codec.ProtocolDecoderOutput;  
  
/** 
 *  解码器可继承ProtocolEncoderAdapter，但它不利于处理粘包的情况 
 */  
public class MyDecoder extends CumulativeProtocolDecoder {  
      
     @Override  
    protected boolean doDecode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput decoderOutput)   
            throws Exception {  
        CharsetDecoder de = Charset.forName("utf-8").newDecoder();  
  
        /** 
         * 这里，如果长度不够20字节，就认为这条消息还没有累积完成 
         */  
        if(buffer.remaining() < 20){  
            /** 
             *      停止调用decode，但如果还有数据没有读取完，将含有剩余数据的IoBuffer保存到IoSession中， 
             *  当有新数据包来的时候再和新的合并后再调用decoder解码 
             *      注意：没有消费任何数据时不能返回true，否则会抛出异常 
             */  
            return false;  
              
        }else{  
              
            int length = buffer.getInt(); //4个字节 
              
            MyMsg msg = new MyMsg();  
              
            msg.setSender(buffer.getLong());  //8个字节
            msg.setReceiver(buffer.getLong());  //8个字节
              
            //  注意：20 = 消息长度的字节 + 发送人和接收人的字节  
            msg.setContent(buffer.getString(length - 20, de));  
              
            decoderOutput.write(msg);  
              
            /** 
             *  CumulativeProtocolDecoder会再次调用decoder，并把剩余的数据发下来继续解码 
             */  
            return true;  
        }  
    }  
  
} 