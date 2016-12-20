package com.nio.test4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/*******************************
 * 
 * 文件读取Demo
 * @author maoman
 *
 ********************************/
public class Filenio {
    
	public static void main(String args[]) throws IOException{
		
		File file=new File("/home/maoman/test.sh");
		FileInputStream inputStream=new FileInputStream(file);
		FileChannel channel = inputStream.getChannel();
		
		ByteBuffer byteBuffer=ByteBuffer.allocate(20);
		int size=channel.read(byteBuffer);

		System.out.println(new String(byteBuffer.array()));

		
		channel.close();
		inputStream.close();
	
		
	}
}