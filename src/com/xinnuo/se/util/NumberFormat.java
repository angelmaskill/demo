package com.xinnuo.se.util;

import java.text.DecimalFormat;

public class NumberFormat {

	
	public static String format(String num,int digits)
	{
		String f = "0";
		
		for(int i=0;i<digits;i++)
		{
			if(i==0)
			{
				f += ".";
			}
			
			f += "0";
		}
		
		
		DecimalFormat df = new DecimalFormat(f);
		
		if(num==null || "".equals(num.trim()) || "null".equals(num))
		{
			num = "0";
		}
		
		double n = Double.parseDouble(num);
		
		return df.format(n).toString();

		
	}
	
	public static void main(String[] ars)throws Exception
	{
		String teString = format("11115.999",0);
		
		System.out.println(teString);
	}
}
