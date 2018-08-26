package com.mina.test5;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.mina.test5.Server;

/**
 * @ClassName KeepAliveMessageFactoryImpl
 * @Description 内部类，实现KeepAliveMessageFactory（心跳工厂）
 * 
     * 这里的执行顺序是  分两条顺序 
     * getResponse()---->isResponse();获取数据判断心跳事件（目的是判断是否触发心跳超时异常）
     * isRequest()----->getRequest(); 写回数据是心跳事件触发的数据（目的写回给服务器（客户端）心跳包）
     * 两条路线  没必要都实现行
    
 * @author cruise
 * 
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
	/** 心跳包内容 */
	
	private static final String HEARTBEATREQUEST = "ALIVE";
    private static final String HEARTBEATRESPONSE = "YES";
    private static final String HEARTBEATRESPONSEmeta = "WAITING! I AM COMMING!";
    
    

	/**
	 * 判断是否心跳请求包 是的话返回true
	 * 在我们的项目中除了心跳包还有业务包
	         在这里我们必须有个了断，干嘛？判断客户端发送的是否为心跳包 
	   判断客户端发来心跳请求信息是否是规定字符串.
	 */
	@Override
	public boolean isRequest(IoSession session, Object message) {
		if (message.equals(HEARTBEATREQUEST)){
			Server.LOG.info("接收到客户端心跳询问事件,心跳数据包是》》" + message);
			return true;
		}
		return false;
	}

	/**
	 * 由于被动型心跳机制，没有请求当然也就不关注反馈 因此直接返回false
	 */
	@Override
	public boolean isResponse(IoSession session, Object message) {
		return false;
	}

	/**
	 * 被动型心跳机制无请求 因此直接返回null
	 */
	@Override
	public Object getRequest(IoSession session) {
		/** 返回预设语句 */
		Server.LOG.info("收到客户端的请求心跳包信息: ");
		return null;
	}

	/**
	 * 根据心跳请求request 反回一个心跳反馈消息non-nul 
	 * 服务器端拿到客户端心跳请求之后,进行心跳回复.
	 */
	@Override
	public Object getResponse(IoSession session, Object request) {
		Server.LOG.info("服务器端构建信息,进行回复: " + HEARTBEATRESPONSE);
		/** 返回预设语句 */
		return HEARTBEATRESPONSE;
	}

	
}