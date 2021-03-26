package com.netty.NettyStickyPacket.selfConfig.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class MyDecoder extends FrameDecoder {

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
                            ChannelBuffer buffer) throws Exception {
        // 基本长度（至少要有长度头那么长）  
        int baseLength = 4;
        if (buffer.readableBytes() > baseLength) {
            // 防止Socket攻击  
            if (buffer.readableBytes() > 2048) {
                buffer.skipBytes(buffer.readableBytes());
            }
            // 标记  
            buffer.markReaderIndex();
            // 长读取度头  
            int length = buffer.readInt();
            // 长度不够  
            if (buffer.readableBytes() < length) {
                // 还原到上述标记位置  
                buffer.resetReaderIndex();
                // 缓存当前剩余的buffer数据，等待剩下数据包到来  
                return null;
            }
            // 读数据  
            byte[] bytes = new byte[length];
            buffer.readBytes(bytes);
            // 往下传递对象  
            return new String(bytes);
        }
        // 缓存当前剩余的buffer数据，等待剩下数据包到来  
        return null;
    }

}  