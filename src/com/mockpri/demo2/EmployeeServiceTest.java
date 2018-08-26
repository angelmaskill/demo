package com.mockpri.demo2;

/**
 * @Author: yanlu.myl
 * @Description: 模拟静态方法
 * @Date: Created on 2018/8/26 16:39
 * @Modified By:
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Employee.class)
public class EmployeeServiceTest {

    @Test
    public void shouldReturnTheCountOfEmployeesUsingTheDomainClass() {

        PowerMockito.mockStatic(Employee.class);
        PowerMockito.when(Employee.count()).thenReturn(900);

        EmployeeService employeeService = new EmployeeService();
        assertEquals(900, employeeService.getEmployeeCount());
    }

    /**
     * 人工跳过
     */
    @Test
    public void shouldReturnTrueWhenIncrementOf10PercentageIsGivenSuccessfully() {

        PowerMockito.mockStatic(Employee.class);
        //PowerMockito.doNothing方法告诉PowerMock下一个方法调用时什么也不做。
        PowerMockito.doNothing().when(Employee.class);
        Employee.giveIncrementOf(10);
        EmployeeService employeeService = new EmployeeService();
        assertTrue(employeeService.giveIncrementToAllEmployeesOf(10));

    }

    /**
     * 人工抛异常
     */
    @Test
    public void shouldReturnFalseWhenIncrementOf10PercentageIsNotGivenSuccessfully() {

        PowerMockito.mockStatic(Employee.class);
        //PowerMockito.doThrow方法告诉PowerMock下一个方法调用时产生异常。
        PowerMockito.doThrow(new IllegalStateException()).when(Employee.class);
        Employee.giveIncrementOf(10);
        EmployeeService employeeService = new EmployeeService();
        assertFalse(employeeService.giveIncrementToAllEmployeesOf(10));
    }


    /**
     * 验证public方法调用
     */
    @Test
    public void shouldCreateNewEmployeeIfEmployeeIsNew() {

        Employee mock = PowerMockito.mock(Employee.class);
        PowerMockito.when(mock.isNew()).thenReturn(true);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee2(mock);
        Mockito.verify(mock).create();
        Mockito.verify(mock, Mockito.never()).update();
    }

    /**
     * 验证静态方法
     */
    @Test
    public void shouldInvoke_giveIncrementOfMethodOnEmployeeWhileGivingIncrement() {

        PowerMockito.mockStatic(Employee.class);
        PowerMockito.doNothing().when(Employee.class);
        Employee.giveIncrementOf(9);
        EmployeeService employeeService = new EmployeeService();
        employeeService.giveIncrementToAllEmployeesOf(9);
        PowerMockito.verifyStatic();
        Employee.giveIncrementOf(9);
    }

    /**
     * 验证调用的顺序
     */
    @Test
    public void shouldInvokeIsNewBeforeInvokingCreate() {
        Employee mock = PowerMockito.mock(Employee.class);
        PowerMockito.when(mock.isNew()).thenReturn(true);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee2(mock);
        InOrder inOrder = Mockito.inOrder(mock);
        inOrder.verify(mock).isNew();
        Mockito.verify(mock).create();
        Mockito.verify(mock, Mockito.never()).update();
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
}

class EmployeeService {

    public int getEmployeeCount() {
        return Employee.count();
    }

    public void saveEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public void saveEmployee2(Employee employee) {
        if (employee.isNew()) {
            employee.create();
            return;
        }
        employee.update();
    }

    public boolean giveIncrementToAllEmployeesOf(int percentage) {
        try {
            Employee.giveIncrementOf(percentage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

final class EmployeeIdGenerator {

    public final static int getNextId() {
        throw new UnsupportedOperationException();
    }
}