package com.designpattern.Chapter18Command.sample01;

public class TVChangeCommand implements AbstractCommand
{
	private Television tv;
	public TVChangeCommand()
	{
		tv = new Television();
	}
	public void execute()
	{
		tv.changeChannel();
	}
}