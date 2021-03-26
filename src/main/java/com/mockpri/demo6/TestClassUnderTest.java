package com.mockpri.demo6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

/**
 * @author yanlu.myl
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/26 21:46
 * @Modified By:
 * @date 2018/08/26
 */
@RunWith(PowerMockRunner.class)
public class TestClassUnderTest {

    @Test
    public void testCallArgumentInstance() {
        File file = PowerMockito.mock(File.class);
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(underTest.callArgumentInstance(file));
    }

    @Test
    @PrepareForTest(ClassUnderTest.class)
    public void testCallInternalInstance() throws Exception {
        File file = PowerMockito.mock(File.class);
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(underTest.callInternalInstance("bbb"));
    }

    @Test
    @PrepareForTest(ClassDependency.class)
    public void testCallFinalMethod() {

        ClassDependency depencency = PowerMockito.mock(ClassDependency.class);
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.when(depencency.isAlive()).thenReturn(true);
        Assert.assertTrue(underTest.callFinalMethod(depencency));
    }

    @Test
    @PrepareForTest(ClassDependency.class)
    public void testCallStaticMethod() {
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.mockStatic(ClassDependency.class);
        PowerMockito.when(ClassDependency.isExist()).thenReturn(true);
        Assert.assertTrue(underTest.callStaticMethod());
    }

    @Test
    @PrepareForTest(ClassUnderTest.class)
    public void testCallPrivateMethod() throws Exception {
        ClassUnderTest underTest = PowerMockito.mock(ClassUnderTest.class);
        PowerMockito.when(underTest.callPrivateMethod()).thenCallRealMethod();
        PowerMockito.when(underTest, "isExist").thenReturn(true);
        Assert.assertTrue(underTest.callPrivateMethod());
    }

    @Test
    @PrepareForTest(ClassUnderTest.class)
    public void testCallSystemFinalMethod() {
        String str = PowerMockito.mock(String.class);
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.when(str.isEmpty()).thenReturn(false);
        Assert.assertFalse(underTest.callSystemFinalMethod(str));
    }

    @Test
    @PrepareForTest(ClassUnderTest.class)
    public void testCallSystemStaticMethod() {
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getProperty("aaa")).thenReturn("bbb");
        Assert.assertEquals("bbb", underTest.callSystemStaticMethod("aaa"));
    }
}

class ClassUnderTest {

    public boolean callArgumentInstance(File file) {
        return file.exists();
    }

    public boolean callInternalInstance(String path) {
        File file = new File(path);
        return file.exists();
    }

    public boolean callFinalMethod(ClassDependency refer) {
        return refer.isAlive();
    }

    public boolean callSystemFinalMethod(String str) {
        return str.isEmpty();
    }

    public boolean callStaticMethod() {
        return ClassDependency.isExist();
    }

    public String callSystemStaticMethod(String str) {
        return System.getProperty(str);
    }

    public boolean callPrivateMethod() {
        return isExist();
    }

    private boolean isExist() {
        // do something
        return false;
    }
}

class ClassDependency {

    public static boolean isExist() {
        // do something
        return false;
    }

    public final boolean isAlive() {
        // do something
        return false;
    }
}