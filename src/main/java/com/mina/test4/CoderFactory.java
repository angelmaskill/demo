package com.mina.test4;

import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;



public class CoderFactory implements ProtocolCodecFactory {

	private ByteArrayDecoder decoder;
    private ByteArrayEncoder encoder;
    
    public CoderFactory() {
    	encoder = new ByteArrayEncoder();
        decoder = new ByteArrayDecoder();
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return (ProtocolDecoder) decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return (ProtocolEncoder) encoder;
    }
}