/**
 * @(#)TestLog4j.java
 * 
 * Copyright Oristand.All rights reserved. This software is the XXX system.
 * 
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2013-12-20     Administrator    Created
 **********************************************
 */

package com.xinnuo.se.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2013-12-20
 */
public class TestLog4j {

    protected  Log log = LogFactory.getLog(this.getClass());

   
    
    public TestLog4j() {
        PropertyConfigurator.configure(PathUtil.getWebInfPath()+"log4j.properties");
    }

    public void testLog4j() {
      
        if (log.isDebugEnabled()) {
            log.debug("111");
        }
        if (log.isInfoEnabled()) {
            log.info("222");
        }
        if (log.isWarnEnabled()) {
            log.warn("333");
        }
        if (log.isErrorEnabled()) {
            log.error("444");
        }
        if (log.isFatalEnabled()) {
            log.fatal("555");
        }
    }

    public static void main(String[] args) {
        TestLog4j t = new TestLog4j();
        t.testLog4j();
    }
}
