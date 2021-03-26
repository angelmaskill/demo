package com.mina.utils;

import com.alibaba.fastjson.JSON;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


public class IobufferUtils {
    public static final CharsetDecoder decoder = (Charset.forName("UTF-8"))
            .newDecoder();

    /**
     * @return
     */
    public IoBuffer creatIoBuffer() {
        return IoBuffer.allocate(0x40).setAutoExpand(true);
    }

    /**
     * @param capacity
     * @return
     */
    public IoBuffer creatIoBuffer(int capacity) {
        return IoBuffer.allocate(capacity).setAutoExpand(true);
    }


    /**
     * 解析buffer数据为json对象
     *
     * @param buffer
     * @param clazz  java bean class
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object parseObject(IoBuffer buffer, Class clazz) throws CharacterCodingException {
        Object o = JSON.parseObject(buffer.getString(decoder), clazz);
        return o;
    }

    /**
     * read a byte (1 code)
     *
     * @param buffer buffer
     * @return byte
     */
    public byte readByte(IoBuffer buffer) {
        return buffer.get();
    }

    /**
     * read a short (2 code)
     *
     * @param buffer buffer
     * @return short
     */
    public short readShort(IoBuffer buffer) {
        return buffer.getShort();
    }

    /**
     * read a short (4 code)
     *
     * @param buffer buffer
     * @return int
     */
    public int readInt(IoBuffer buffer) {
        return buffer.getInt();  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @param buffer 待处理数据缓存区
     * @return message
     * 如果默认读取1字节(readBytes())长度的字符串，那么该方法传入buffer将直接返回相应的字符串
     * 编码格式:utf-8
     */
    public String readStringUTF8(IoBuffer buffer) throws CharacterCodingException {
        int l = buffer.get();
        return buffer.getString(l, decoder);
    }

    /**
     * @param buffer 待处理数据缓存区
     * @param length 读取指定的数据长度  readBytes() ,readShort() or readLong();
     * @return message
     * 编码格式:utf-8
     */
    public String readStringUTF8(IoBuffer buffer, int length) throws CharacterCodingException {
        return buffer.getString(length, decoder);
    }

    /**
     * 构建数据包 ，处理一级类型数据包
     * 注意：此方法适应的协议是：
     * paras数据结构-----> length+msg+length+msg ... ...
     * length: byte(1字节),short（2字节）,int（4字节）
     *
     * @param arg1 类型1  注意 ：args1<0
     * @return buffer
     */
    public IoBuffer getWriteBufferA(int arg1, Object... paras) {
        return getWriteBuffer(arg1, 0, null, paras);
    }

    /**
     * 构建数据包 ，处理二级类型数据包
     *
     * @param arg1  类型1
     * @param arg2  类型2
     * @param paras 待发送的数据
     *              paras数据-----> length+msg+length+msg ... ...
     *              length: byte(1字节),short（2字节）,int（4字节）
     * @return buffer
     */
    public IoBuffer getWriteBufferB(int arg1, int arg2, Object... paras) {
        return getWriteBuffer(arg1, arg2, null, paras);  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 构建数据包
     *
     * @param arg1   类型1
     * @param arg2   类型2
     * @param buffer 申请的buf
     * @param paras  待发送的数据
     *               paras数据-----> length+msg+length+msg ... ...
     *               length: byte(1字节),short（2字节）,int（4字节）
     * @return buffer
     */
    private IoBuffer getWriteBuffer(int arg1, int arg2, IoBuffer buffer, Object... paras) {
        if (buffer == null) {
            buffer = IoBuffer.allocate(0x40).setAutoExpand(true);
        }
        buffer.putShort(Short.MIN_VALUE);//包长占2字节
        buffer.put((byte) arg1);
        if (arg2 != 0) buffer.put((byte) arg2);
        for (Object para : paras) {
            if (para instanceof Byte) {
                buffer.put((Byte) para);  // 占1字节
            } else if ((para instanceof String)) {
                buffer.put(((String) para).getBytes());
            } else if (para instanceof Integer) {
                buffer.putInt((Integer) para);    //占4字节
            } else if (para instanceof Short) {
                buffer.putShort((Short) para);  //占2字节
            }
        }
        buffer.putShort(0, (short) (buffer.position() - 0x2));
        buffer.flip();
        return buffer;
    }
}
