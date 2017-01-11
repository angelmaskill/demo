package com.mina.test2;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.mina.utils.Tools;

public class MainMessageEncoder extends ProtocolEncoderAdapter {
	private Logger logger = Logger.getLogger(MainMessageEncoder.class);
	private Charset charset;

	public MainMessageEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		if (!(message instanceof MessageModel)) {
			return;
		}
		MessageModel mm = (MessageModel) message;
		String mess = mm.getData();
		int machNo = Integer.parseInt(mess.substring(12, 18), 16);
		int type = 1;

		if (type == 0) {
			IoBuffer buf = IoBuffer.allocate(mess.length()).setAutoExpand(true);
			buf.putString(mess, charset.newEncoder());
			buf.flip();
			out.write(buf);
		} else {
			IoBuffer buf = IoBuffer.allocate(500).setAutoExpand(true);
			byte[] content = Tools.HexString2Bytes(mess);
			buf.put(content);
			buf.flip();
			out.write(buf);
		}

	}

}
