package com.mockpri.demo2;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/26 17:05
 * @Modified By:
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

public class EmployeeTest {

    /**
     * 屏蔽异常
     */
    @Test()
    public void shouldNotDoAnythingIfEmployeeWasSaved() {

        Employee2 employee = PowerMockito.mock(Employee2.class);
        PowerMockito.doNothing().when(employee).save();
        try {
            employee.save();
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
    }

    /**
     * 修改异常类型
     */
    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnExceptionIfEmployeeWasNotSaved() {

        Employee2 employee = PowerMockito.mock(Employee2.class);
        PowerMockito.doThrow(new IllegalStateException()).when(employee).save();
        employee.save();
    }
}

class Employee2 {
    public void save() {
        throw new UnsupportedOperationException();
    }
}