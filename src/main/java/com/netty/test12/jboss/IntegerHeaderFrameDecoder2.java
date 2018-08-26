package com.netty.test12.jboss;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;

public class IntegerHeaderFrameDecoder2 extends ReplayingDecoder {
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, Enum state)
			throws Exception {
		return buffer.readBytes(buffer.readInt());
	}
}
