package com.thread.testThreadLocal;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接管理类
 * 
 * @author 爽
 * 
 */
public class ConnectionManager {

	/** 线程内共享Connection，ThreadLocal通常是全局的，支持泛型 */
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	public static Connection getCurrConnection() {
		// 获取当前线程内共享的Connection
		Connection conn = threadLocal.get();
		try {
			// 判断连接是否可用
			if (conn == null || conn.isClosed()) {
				// 创建新的Connection赋值给conn(略)
				// 保存Connection
				threadLocal.set(conn);
			}
		} catch (SQLException e) {
			// 异常处理
		}
		return conn;
	}

	/**
	 * 关闭当前数据库连接
	 */
	public static void close() {
		// 获取当前线程内共享的Connection
		Connection conn = threadLocal.get();
		try {
			// 判断是否已经关闭
			if (conn != null && !conn.isClosed()) {
				// 关闭资源
				conn.close();
				// 移除Connection
				threadLocal.remove();
				conn = null;
			}
		} catch (SQLException e) {
			// 异常处理
		}
	}
}