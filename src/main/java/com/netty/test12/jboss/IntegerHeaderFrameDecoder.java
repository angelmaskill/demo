package com.netty.test12.jboss;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class IntegerHeaderFrameDecoder extends FrameDecoder {
	@Override
	protected Object decode(org.jboss.netty.channel.ChannelHandlerContext ctx, org.jboss.netty.channel.Channel channel,
			ChannelBuffer buf) throws Exception {
		if (buf.readableBytes() < 4) {
			return null;
		}
		buf.markReaderIndex();
		int length = buf.readInt();
		if (buf.readableBytes() < length) {
			buf.resetReaderIndex();
			return null;
		}
		return buf.readBytes(length);
	}
}