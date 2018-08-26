/**
 * @(#)Jdbc.java
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
 *  1     2014-11-4     Administrator    Created
 **********************************************
 */

package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.TestCase;

/**
 * Class description goes here.
 * 
 * @author Administrator
 * @since 2014-11-4
 */

public class Jdbc extends TestCase {
    public void testConnect() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@125.46.57.68:60021:orcl";
        String user = "nccmoltp_hns";
        String password = "oracle_2009_gfdatabase";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
