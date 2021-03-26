/**
 * @(#)HelloDao.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: webservice
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-10-21     Administrator    Created
 **********************************************
 */

package com.xinnuo.se.util;

import org.apache.log4j.Logger;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-10-21
 */
public class HelloLog4j {
    private static Logger logger = Logger.getLogger(HelloLog4j.class);

    public static void main(String[] args) {
        logger.debug("This is debug message from Dao.");

        logger.info("This is info message from Dao.");

        logger.error("This is error message from Dao.");
    }
}
