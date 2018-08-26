
package com.log4j;
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-2-26     Administrator    Created
 **********************************************
 */

import java.util.List;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testlog4j2 extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(Testlog4j2.class);
    
    private static final   Logger chargeLogger = LoggerFactory.getLogger("charge_log");  
    private static final   Logger goldLogger = LoggerFactory.getLogger("gold_log");  
    private static final   Logger propLogger = LoggerFactory.getLogger("prop_log");  
    private static final   Logger registerLogger = LoggerFactory.getLogger("register_log");  
    private static final   Logger activeLogger = LoggerFactory.getLogger("active_log");  
    private static final   Logger gamePlayLogger = LoggerFactory.getLogger("game_play_log");  
    private static final   Logger onlineLogger = LoggerFactory.getLogger("online_log");  
    
   /* public void test1() {
        logger.debug("This is debug message");
        logger.info("This is info message");
        logger.warn("This is warn message");
        logger.error("This is error message");
    }*/
    
    public void test2() {
        chargeLogger.debug("This is debug message");
        chargeLogger.info("This is info message");
        chargeLogger.warn("This is warn message");
        chargeLogger.error("This is error message");
    }
}
