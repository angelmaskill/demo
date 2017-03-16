package com.designpattern.Chapter06AbstractFactory.sample01;

public class TCLFactory implements EFactory
{
	public Television produceTelevision()
	{
		return new TCLTelevision();
	}
	
	public AirConditioner produceAirConditioner()
	{
		return new TCLAirConditioner();
	}
}