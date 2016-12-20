package com.netty.test8.client.handler;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;

import com.netty.test8.message.BaseMsg;
import com.netty.test8.message.Constants;
import com.netty.test8.message.LoginMsg;
import com.netty.test8.message.LoginReplyMsg;
import com.netty.test8.message.MsgType;
import com.netty.test8.message.PingMsg;
import com.netty.test8.message.ReplyClientBody;
import com.netty.test8.message.ReplyMsg;
import com.netty.test8.message.ReplyServerBody;

/**
 * @author buer
 * @since 16/6/28
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMsg> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {

		MsgType type = msg.getType();
		switch (type) {
		case LOGIN:{
			LoginMsg loginMsg = new LoginMsg();
			loginMsg.setUserName("buer");
			loginMsg.setPassword("buer");
			ctx.writeAndFlush(loginMsg);
		}
		break;
		case PING:{
			System.out.println("receive ping from server------->" + new Date());
		}
		break;
		case ASK:{
			ReplyClientBody clientBody = new ReplyClientBody("reply client body");
			ReplyMsg replyMsg = new ReplyMsg();
			replyMsg.setReplyBody(clientBody);
			ctx.writeAndFlush(replyMsg);

		}
		break;
		case REPLY:{
			ReplyMsg replyMsg = (ReplyMsg)msg;
			ReplyServerBody replyServerBody = (ReplyServerBody)replyMsg.getReplyBody();
			System.out.println("receive client msg : " + replyServerBody);
		}
		break;
		case LOGIN_REPLY:{
			LoginReplyMsg loginReplyMsg = (LoginReplyMsg)msg;
			Constants.setClientId(loginReplyMsg.getLoginToken());
		}
		break;
		default:
			break;
		}
	}

	/**
	 * 利用空闲时间发送心跳
	 *
	 * @param ctx
	 * @param evt
	 * @throws Exception
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			switch (e.state()) {
			case WRITER_IDLE: {
				PingMsg pingMsg = new PingMsg();
				ctx.writeAndFlush(pingMsg);
				System.out.println("send ping to server for idle--- " + new Date());

			}
			default:
				break;
			}
		}
		super.userEventTriggered(ctx, evt);
	}
}
