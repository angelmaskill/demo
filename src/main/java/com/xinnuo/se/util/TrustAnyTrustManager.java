/**
 * @(#)TrustAnyTrustManager.java Copyright Oristand.All rights reserved. This software is the XXX system.
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

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2014-5-20
 */
public class TrustAnyTrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
    }

}
