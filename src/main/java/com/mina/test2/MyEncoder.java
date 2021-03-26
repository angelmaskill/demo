package com.mina.test2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * 编码器
 */
public class MyEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput encoderOutput)
            throws Exception {

        CharsetEncoder ce = Charset.forName("utf-8").newEncoder();

        MyMsg msg = (MyMsg) message;

        //  Mina IoBuffer  
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);

        buffer.putInt(msg.getLenth());
        buffer.putLong(msg.getSender());
        buffer.putLong(msg.getReceiver());

        //  有多个可变长度的属性时，可约定通过定义可变属性的最大长度(多余截取不足补齐)或put之前put其长度等方式处理  
        buffer.putString(msg.getContent(), ce);

        buffer.flip();

        encoderOutput.write(buffer);

    }

}  
