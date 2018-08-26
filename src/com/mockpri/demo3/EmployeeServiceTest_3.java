package com.mockpri.demo3;

/**
 * @Author: yanlu.myl
 * @Description: 模拟final类或方法
 * @Date: Created on 2018/8/26 16:39
 * @Modified By:
 */

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EmployeeIdGenerator.class, EmployeeService.class})
public class EmployeeServiceTest_3 {
    public boolean callInternalInstance(String path) {
        File file = new File(path);
        return file.exists();
    }


    @Test
    public void shouldGenerateEmployeeIdIfEmployeeIsNew() {

        Employee mock = PowerMockito.mock(Employee.class);
        PowerMockito.when(mock.isNew()).thenReturn(true);
        PowerMockito.mockStatic(EmployeeIdGenerator.class);
        PowerMockito.when(EmployeeIdGenerator.getNextId()).thenReturn(90);
        EmployeeService employeeService = new
                EmployeeService();
        employeeService.saveEmployee(mock);
        PowerMockito.verifyStatic();
        EmployeeIdGenerator.getNextId();
        Mockito.verify(mock).setEmployeeId(90);
        Mockito.verify(mock).create();
    }

    /**
     * 模拟构造函数
     * @throws Exception
     */
    @Test
    public void shouldSendWelcomeEmailToNewEmployees() throws Exception {

        Employee employeeMock = PowerMockito.mock(Employee.class);
        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
        PowerMockito.mockStatic(EmployeeIdGenerator.class);
        WelcomeEmail welcomeEmailMock = PowerMockito.mock(WelcomeEmail.class);
        //whenNew().withArguments(…).thenReturn()是对构造方法的mock模式
        PowerMockito.whenNew(WelcomeEmail.class).withArguments(employeeMock, "Welcome to Mocking with PowerMock How-to!").thenReturn(welcomeEmailMock);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee_2(employeeMock);
        //verifyNew().withArguments()是验证模式
        PowerMockito.verifyNew(WelcomeEmail.class).withArguments(employeeMock, "Welcome to Mocking with PowerMock How-to!");
        Mockito.verify(welcomeEmailMock).send();
    }

    /**
     * 使用spy进行部分模拟
     * 注意spy只能使用PowerMockito.doNothing()/doReturn()/doThrow()。
     */
    @Test
    public void shouldInvokeTheCreateEmployeeMethodWhileSavingANewEmployee() {

        final EmployeeService spy = PowerMockito.spy(new EmployeeService());
        final Employee employeeMock = PowerMockito.mock(Employee.class);
        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
        PowerMockito.doNothing().when(spy).createEmployee(employeeMock);
        spy.saveEmployee_3(employeeMock);
        Mockito.verify(spy).createEmployee(employeeMock);
    }

    /**
     * PowerMockito.whenNew方法时，必须加注解@PrepareForTest和@RunWith
     * 注解@PrepareForTest里写的类是需要mock的new对象代码所在的类。
     * @throws Exception
     */
    @Test
    @PrepareForTest(EmployeeServiceTest_3.class)
    public void testCallInternalInstance() throws Exception {
        File file = PowerMockito.mock(File.class);
        EmployeeServiceTest_3 underTest = new EmployeeServiceTest_3();
        // 当遇到初始化file的时候,就传递参数bbb,返回file对象.
        PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
        // 当遇到file 的exists 方法调用的时候,直接返回true
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(underTest.callInternalInstance("bbb"));
    }

    /**
     * mock静态方法的返回值.
     * 说明： 当需要mock final方法的时候，必须加注解@PrepareForTest和@RunWith。
     * 注解@PrepareForTest里写的类是final方法所在的类。
     */
    @Test
    @PrepareForTest(Employee.class)
    public void testCallFinalMethod() {

        final Employee employeeMock = PowerMockito.mock(Employee.class);
        EmployeeService underTest = new EmployeeService();

        PowerMockito.when(employeeMock.isAlive()).thenReturn(true);

        Assert.assertTrue(underTest.callFinalMethod(employeeMock));

    }


    @Test
    public void shouldInvokeTheCreateEmployeeMethodWhileSavingANewEmployee_2() throws Exception {

        final EmployeeService spy = PowerMockito.spy(new EmployeeService());
        final Employee employeeMock = PowerMockito.mock(Employee.class);
        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
        PowerMockito.doNothing().when(spy, "createEmployee_4", employeeMock);
        spy.saveEmployee_4(employeeMock);
        PowerMockito.verifyPrivate(spy).invoke("createEmployee_4", employeeMock);
    }
}

class Employee {
    public static int count() {
        throw new UnsupportedOperationException();
    }

    public static void giveIncrementOf(int percentage) {
        throw new UnsupportedOperationException();
    }

    public boolean isNew() {
        throw new UnsupportedOperationException();
    }

    public void update() {
        throw new UnsupportedOperationException();
    }

    public void create() {
        throw new UnsupportedOperationException();
    }

    public void setEmployeeId(int nextId) {
        throw new UnsupportedOperationException();
    }

    public final boolean isAlive() {
        // do something
        return false;
    }
}

class EmployeeService {

    public void saveEmployee(Employee employee) {
        if (employee.isNew()) {
            employee.setEmployeeId(EmployeeIdGenerator.getNextId());
            employee.create();
            return;
        }
        employee.update();
    }

    public void saveEmployee_2(Employee employee) {
        if (employee.isNew()) {
            employee.setEmployeeId(EmployeeIdGenerator.getNextId());
            employee.create();
            WelcomeEmail emailSender = new WelcomeEmail(employee,
                    "Welcome to Mocking with PowerMock How-to!");
            emailSender.send();
            return;
        }
        employee.update();
    }
    public void saveEmployee_3(Employee employee) {
        if(employee.isNew()) {
            createEmployee(employee);
            return;
        }
        employee.update();
    }
    public void saveEmployee_4(Employee employee) {
        if(employee.isNew()) {
            createEmployee_4(employee);
            return;
        }
        employee.update();
    }

    void createEmployee(Employee employee) {
        employee.setEmployeeId(EmployeeIdGenerator.getNextId());
        employee.create();
        WelcomeEmail emailSender = new WelcomeEmail(employee,
                "Welcome to Mocking with PowerMock How-to!");
        emailSender.send();
    }

    private void createEmployee_4(Employee employee) {
        employee.setEmployeeId(EmployeeIdGenerator.getNextId());
        employee.create();
        WelcomeEmail emailSender = new WelcomeEmail(employee,
                "Welcome to Mocking with PowerMock How-to!");
        emailSender.send();
    }

    public boolean callFinalMethod(Employee employeeMock) {
        return employeeMock.isAlive();
    }
}

final class EmployeeIdGenerator {

    public final static int getNextId() {
        throw new UnsupportedOperationException();
    }
}

class WelcomeEmail {

    public WelcomeEmail(final Employee employee, final String message) {
        throw new UnsupportedOperationException();
    }

    public void send() {
        throw new UnsupportedOperationException();
    }
}