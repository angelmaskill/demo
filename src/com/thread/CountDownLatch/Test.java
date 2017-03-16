package com.thread.CountDownLatch;

/**
 * 
 * <p>
 * Description: CountDownLatch的学习
 * </p>
 * 
 * @author zhangjunshuai
 * @version 1.0 Create Date: 2014-9-25 下午8:11:55 Project Name: Java7Thread
 *
 *          <pre>
 * Modification History: 
  *             Date                                Author                   Version          Description 
 * -----------------------------------------------------------------------------------------------------------  
 * LastChange: $Date::             $      $Author: $          $Rev: $
 *          </pre>
 *
 */
public class Test {

	/**
	 * <p>
	 * </p>
	 * 
	 * @author zhangjunshuai
	 * @date 2014-9-25 下午8:11:50
	 * @param args
	 */
	public static void main(String[] args) {
		Videoconference conference = new Videoconference(9);
		Thread threadConference = new Thread(conference);
		threadConference.start();
		for (int i = 0; i < 10; i++) {
			Participant p = new Participant(conference, "Participant" + i);
			Thread t = new Thread(p);
			t.start();
		}
	}

}
