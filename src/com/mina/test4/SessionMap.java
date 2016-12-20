package com.mina.test4;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class SessionMap {
	private final static Logger logger = Logger.getLogger(SessionMap.class);
	public static Map<String, IoSession> SESSION_MAP = new HashMap<String, IoSession>();

	public static void registerSession(LoginBean bean, IoSession IoSession) {
		synchronized (SESSION_MAP) {
			IoSession.setAttribute("USERNAME", bean);
			SessionMap.SESSION_MAP.put(getSessionid(bean.getId()), IoSession);
			logger.info("注册Session成功");
		}
	}

	public static IoSession getSession(String username) {
		synchronized (SESSION_MAP) {
			return SessionMap.SESSION_MAP.get(getSessionid(username));
		}
	}

	public static void unregisterSession(String username) {
		synchronized (SESSION_MAP) {
			SessionMap.SESSION_MAP.remove(getSessionid(username));
			logger.info("注销sessionid成功");
		}
	}

	public static String getSessionid(String username) {
		return username.toUpperCase();
	}
}