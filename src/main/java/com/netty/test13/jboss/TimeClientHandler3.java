package com.netty.test13.jboss;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

class TimeClientHandler3 extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Persons person = (Persons) e.getMessage();
		System.out.println(person);
		e.getChannel().close();
	}
}