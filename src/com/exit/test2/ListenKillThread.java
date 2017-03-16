package com.exit.test2;

import org.apache.log4j.Logger;

/**
 * @version 1.00,2016年7月17日下午8:46:51
 * @since 1.5
 * @author arno
 */
public class ListenKillThread extends Thread {

	// 控制是否从kafka中取数据的开关.true则不消费数据.
	public volatile static boolean FLAG = false;
	private Logger logger = Logger.getLogger(ListenKillThread.class);

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		logger.info("====触发程序销毁操作,停止从kafka消费数据,继续消费内存数据... ...");
		// 停止从kafka中消费数据
		FLAG = true;
		int cnt = 0;
		while (FLAG) {
			// 是否消费内存完毕,true是消费完毕
			if (cnt == 10) {
				// 标示内存无堆积，消耗完毕
				break;
			}

			try {
				// 每间隔1秒去判断一下内存数据是否消耗完毕
				Thread.currentThread().sleep(2000);
				cnt++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		logger.info("====内存数据消耗完毕，程序正确退出！");

	}

}
