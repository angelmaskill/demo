package com.designpattern.Chapter10Adapter.sample02;

public class NewCipherAdapter extends DataOperation
{
	private NewCipher cipher;
	
	public NewCipherAdapter()
	{
		cipher=new NewCipher();
	}
	
	public String doEncrypt(int key,String ps)
	{
		return cipher.doEncrypt(key,ps);
	}
}


