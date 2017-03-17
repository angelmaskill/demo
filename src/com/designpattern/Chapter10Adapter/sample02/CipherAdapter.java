package com.designpattern.Chapter10Adapter.sample02;

public class CipherAdapter extends DataOperation
{
	private Caesar cipher;
	
	public CipherAdapter()
	{
		cipher=new Caesar();
	}
	
	public String doEncrypt(int key,String ps)
	{
		return cipher.doEncrypt(key,ps);
	}
}