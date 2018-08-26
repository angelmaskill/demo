package com.nio.test12;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;

public class Test2 {

	/** 
	 * 使用IO读取指定文件的前1024个字节的内容。 
	 * @param file 指定文件名称。 
	 * @throws java.io.IOException IO异常。 
	 */  
	public void ioRead(String file) throws IOException {  
	    FileInputStream in = new FileInputStream(file);  
	    byte[] b = new byte[1024];  
	    in.read(b);  
	    System.out.println(new String(b));  
	}  
	  
	/** 
	 * 使用NIO读取指定文件的前1024个字节的内容。 
	 * @param file 指定文件名称。 
	 * @throws java.io.IOException IO异常。 
	 */  
	public void nioRead(String file) throws IOException {  
	    FileInputStream in = new FileInputStream(file);  
	    FileChannel channel = in.getChannel();  
	  
	    ByteBuffer buffer = ByteBuffer.allocate(1024);  
	    channel.read(buffer);  
	    byte[] b = buffer.array();  
	    System.out.println(new String(b));  
	}  
	
	public void useFloatBuffer(){
		// 分配一个容量为10的新的 float 缓冲区  
        FloatBuffer buffer = FloatBuffer.allocate(10);  
        for (int i = 0; i < buffer.capacity(); i++) {  
            float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));  
            buffer.put(f);  
        }  
        // 反转此缓冲区  
        buffer.flip();  
  
        // 告知在当前位置和限制之间是否有元素  
        while (buffer.hasRemaining()) {  
            float f = buffer.get();  
            System.out.println(f);  
        }  
	}
}
