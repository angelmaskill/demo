package com.designpattern.Chapter08Prototype.sample01;

public class Client
{
	public static void main(String a[])
	{
		Email email,copyEmail;
		
		email=new Email();
		
		copyEmail=(Email)email.clone();
		
		System.out.println("email==copyEmail?");
		System.out.println(email==copyEmail);
		
		System.out.println("email.getAttachment==copyEmail.getAttachment?"); 
		System.out.println(email.getAttachment()==copyEmail.getAttachment());			
	}
}