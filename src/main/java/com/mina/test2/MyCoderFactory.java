package com.mina.test2;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 编解码工厂
 */
public class MyCoderFactory implements ProtocolCodecFactory {

    private MyDecoder decoder;
    private MyEncoder encoder;
//	private MainMessageDecoder decoder;
//	private MainMessageEncoder encoder;

    public MyCoderFactory() {
        this.decoder = new MyDecoder();
        this.encoder = new MyEncoder();
//		this.decoder = new MainMessageDecoder(Charset.forName("utf-8"));
//		this.encoder = new MainMessageEncoder(Charset.forName("utf-8"));
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        System.out.println("进入MyCoderFactory,设置编码器:" + decoder);
        return decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        System.out.println("进入MyCoderFactory,设置解码器:" + encoder);
        return encoder;
    }

}