package com.netty.test14;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.mina.utils.Tools;

public class MainMessageEncoder extends MessageToByteEncoder<MessageModel> {
	private Logger logger = Logger.getLogger(MainMessageEncoder.class);
	private Charset charset;

	public MainMessageEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, MessageModel message, ByteBuf out) throws Exception {
		if (!(message instanceof MessageModel)) {
			return;
		}
		MessageModel mm = (MessageModel) message;
		String mess = mm.getData().toUpperCase();
		int machNo = Integer.parseInt(mess.substring(12, 18), 16);

		int type = ServerImpl.getInstance().getMachMap().get(machNo);
		if (type == 0) {
			byte[] msg = mess.getBytes(charset);
			ByteBuf bf = Unpooled.wrappedBuffer(msg);
			out.writeBytes(bf);
		} else {
			byte[] msg = Tools.HexString2Bytes(mess);
			ByteBuf bf = Unpooled.wrappedBuffer(msg);
			out.writeBytes(bf);
		}
	}
}
