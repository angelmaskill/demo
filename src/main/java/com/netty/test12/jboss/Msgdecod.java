package com.netty.test12.jboss;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;

import com.netty.test12.domain.DecodingState;
import com.netty.test12.domain.Envelope;
import com.netty.test12.domain.Type;
import com.netty.test12.domain.Version;

public class Msgdecod extends ReplayingDecoder {
	private Envelope message;

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, Enum state1)
			throws Exception {

		DecodingState state = (DecodingState) state1;
		switch (state) {
		case VERSION:
			this.message.setVersion(Version.fromByte(buffer.readByte()));
			checkpoint(DecodingState.TYPE);
		case TYPE:
			this.message.setType(Type.fromByte(buffer.readByte()));
			checkpoint(DecodingState.PAYLOAD_LENGTH);
		case PAYLOAD_LENGTH:
			int size = buffer.readInt();
			if (size <= 0) {
				throw new Exception("Invalid content size");
			}
			byte[] content = new byte[size];
			this.message.setPayload(content);
			checkpoint(DecodingState.PAYLOAD);
		case PAYLOAD:
			buffer.readBytes(this.message.getPayload(), 0, this.message.getPayload().length);

			try {
				return this.message;
			} finally {
				this.reset();
			}
		default:
			throw new Exception("Unknown decoding state: " + state);
		}
	}

	private void reset() {
		checkpoint(DecodingState.VERSION);
		this.message = new Envelope();
	}

}
