/**
 * @(#)TrustAnyHostnameVerifier.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2014-5-20     Administrator    Created
 **********************************************
 */

package com.xinnuo.se.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-5-20
 */
public class TrustAnyHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String arg0, SSLSession arg1) {
        // TODO Auto-generated method stub
        return true;
    }

}
