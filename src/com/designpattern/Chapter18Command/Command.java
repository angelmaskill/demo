package com.designpattern.Chapter18Command;

public abstract class Command
{
	public abstract void execute();
}

 class Invoker
{
	private Command command;
	
	public Invoker(Command command)
	{
		this.command=command;
	}
	
	public void setCommand(Command command)
	{
		this.command=command;
	}
	
	public void call()
	{
		command.execute();
	}
}

 class ConcreteCommand extends Command
{
	private Receiver receiver;
	public void execute()
	{
		receiver.action();
	}
}

 class Receiver
{
	public void action()
	{
		//¾ßÌå²Ù×÷
	}
}