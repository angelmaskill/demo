/**
 * @(#)Test.java Copyright Oristand.All rights reserved.
 * This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2015-3-13     Administrator    Created
 **********************************************
 */

package com.refactor.MovingFeaturesBetweenObjects.IntroduceLocalExtension;

import java.util.Date;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2015-3-13
 */
public class Test {
    private static Date nextDay(Date arg) {
        // foreign method, should be on date
        return new Date(arg.getYear(), arg.getMonth(), arg.getDate() + 1);
    }

}
