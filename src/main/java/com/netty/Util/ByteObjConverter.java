package com.netty.Util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;

import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
  
public class ByteObjConverter {  
    public static Object ByteToObject(byte[] bytes) {  
        Object obj = null;  
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);  
        ObjectInputStream oi = null;  
        try {  
            oi = new ObjectInputStream(bi);  
            obj = oi.readObject();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                bi.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                oi.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return obj;  
    }  
  
    public static byte[] ObjectToByte(Object obj) {  
    	
        byte[] bytes = null;  
        ByteArrayOutputStream bo = new ByteArrayOutputStream();  
        ObjectOutputStream oo = null;  
        try {  
            oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
            bytes = bo.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                bo.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                oo.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return (bytes);  
    }

	public static ByteBuf objectToByte(Person msg) {
		ByteBuf bb = new ByteBuf() {

			@Override
			public int refCnt() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean release() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean release(int decrement) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int capacity() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf capacity(int newCapacity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int maxCapacity() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBufAllocator alloc() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteOrder order() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf order(ByteOrder endianness) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf unwrap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isDirect() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int readerIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf readerIndex(int readerIndex) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int writerIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf writerIndex(int writerIndex) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setIndex(int readerIndex, int writerIndex) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int readableBytes() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int writableBytes() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int maxWritableBytes() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isReadable() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReadable(int size) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isWritable() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isWritable(int size) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public ByteBuf clear() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf markReaderIndex() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf resetReaderIndex() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf markWriterIndex() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf resetWriterIndex() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf discardReadBytes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf discardSomeReadBytes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf ensureWritable(int minWritableBytes) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int ensureWritable(int minWritableBytes, boolean force) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean getBoolean(int index) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public byte getByte(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public short getUnsignedByte(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public short getShort(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getUnsignedShort(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getMedium(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getUnsignedMedium(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getInt(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getUnsignedInt(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getLong(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public char getChar(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public float getFloat(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public double getDouble(int index) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf getBytes(int index, ByteBuf dst) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf getBytes(int index, ByteBuf dst, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf getBytes(int index, byte[] dst) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf getBytes(int index, ByteBuffer dst) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf setBoolean(int index, boolean value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setByte(int index, int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setShort(int index, int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setMedium(int index, int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setInt(int index, int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setLong(int index, long value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setChar(int index, int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setFloat(int index, float value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setDouble(int index, double value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setBytes(int index, ByteBuf src) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setBytes(int index, ByteBuf src, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setBytes(int index, byte[] src) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf setBytes(int index, ByteBuffer src) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int setBytes(int index, InputStream in, int length) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf setZero(int index, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean readBoolean() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public byte readByte() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public short readUnsignedByte() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public short readShort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int readUnsignedShort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int readMedium() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int readUnsignedMedium() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int readInt() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long readUnsignedInt() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long readLong() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public char readChar() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public float readFloat() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public double readDouble() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf readBytes(int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readSlice(int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readBytes(ByteBuf dst) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readBytes(ByteBuf dst, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readBytes(ByteBuf dst, int dstIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readBytes(byte[] dst) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readBytes(byte[] dst, int dstIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readBytes(ByteBuffer dst) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf readBytes(OutputStream out, int length) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int readBytes(GatheringByteChannel out, int length) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf skipBytes(int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeBoolean(boolean value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeByte(int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeShort(int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeMedium(int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeInt(int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeLong(long value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeChar(int value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeFloat(float value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeDouble(double value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeBytes(ByteBuf src) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeBytes(ByteBuf src, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeBytes(ByteBuf src, int srcIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeBytes(byte[] src) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeBytes(byte[] src, int srcIndex, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf writeBytes(ByteBuffer src) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int writeBytes(InputStream in, int length) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int writeBytes(ScatteringByteChannel in, int length) throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf writeZero(int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int indexOf(int fromIndex, int toIndex, byte value) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int bytesBefore(byte value) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int bytesBefore(int length, byte value) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int bytesBefore(int index, int length, byte value) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int forEachByte(ByteBufProcessor processor) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int forEachByte(int index, int length, ByteBufProcessor processor) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int forEachByteDesc(ByteBufProcessor processor) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int forEachByteDesc(int index, int length, ByteBufProcessor processor) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuf copy() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf copy(int index, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf slice() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf slice(int index, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf duplicate() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int nioBufferCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ByteBuffer nioBuffer() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuffer nioBuffer(int index, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuffer internalNioBuffer(int index, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuffer[] nioBuffers() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuffer[] nioBuffers(int index, int length) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean hasArray() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public byte[] array() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int arrayOffset() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean hasMemoryAddress() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public long memoryAddress() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String toString(Charset charset) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(int index, int length, Charset charset) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int hashCode() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean equals(Object obj) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int compareTo(ByteBuf buffer) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf retain(int increment) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ByteBuf retain() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		byte[] bytes= ObjectToByte((Object)msg);
		return  bb.getBytes(1, bytes);
	}  
}  