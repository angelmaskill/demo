package com.netty.test12.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class TowIntegerReplayingDecoder extends ReplayingDecoder<Integer> {

	private static final int PARSE_1 = 1;
	private static final int PARSE_2 = 2;
	private int number1;
	private int number2;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		switch (state()) {
		case PARSE_1:
			number1 = in.readInt();
			checkpoint(PARSE_2);
			break;
		case PARSE_2:
			number2 = in.readInt();
			checkpoint(PARSE_1);
			out.add(number1 + number2);
			break;
		default:
			break;
		}

	}

}