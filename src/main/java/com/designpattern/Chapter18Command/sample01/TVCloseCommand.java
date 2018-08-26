package com.designpattern.Chapter18Command.sample01;

public class TVCloseCommand implements AbstractCommand
{
	private Television tv;
	public TVCloseCommand()
	{
		tv = new Television();
	}
	public void execute()
	{
		tv.close();
	}
}