package com.netty.test12.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

import com.netty.test12.domain.DecodingState;
import com.netty.test12.domain.Envelope;
import com.netty.test12.domain.Type;
import com.netty.test12.domain.Version;

/**
 * 里面的checkpoint是当失败后下一次解码的执行点
 * @author mayanlu
 *
 */
public class TowIntegerReplayingDecoder2 extends ReplayingDecoder<DecodingState> {

	private Envelope message;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		switch (state()) {
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
				out.add(message);
			} finally {
				this.reset();
			}
		default:
			throw new Exception("Unknown decoding state: " + state());
		}
	}

	private void reset() {
		checkpoint(DecodingState.VERSION);
		this.message = new Envelope();
	}

}