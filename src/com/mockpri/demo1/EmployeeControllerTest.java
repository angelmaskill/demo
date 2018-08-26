package com.mockpri.demo1;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/26 16:13
 * @Modified By:
 */
public class EmployeeControllerTest {

    public boolean callArgumentInstance(File file) {
        return file.exists();
    }

    /**
     * 人工设置方法调用返回值
     */
    @Test
    public void shouldReturnProjectedCountOfEmployeesFromTheService() {

        EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        //当开始调用到这个getEmployeeCount 的时候,直接返回8.
        PowerMockito.when(mock.getEmployeeCount()).thenReturn(8);
        EmployeeController employeeController = new EmployeeController(mock);
        assertEquals(10, employeeController.getProjectedEmployeeCount());
    }

    /**
     * mock指定方法的调用结果.
     */
    @Test
    public void testCallArgumentInstance() {
        File file = PowerMockito.mock(File.class);
        EmployeeControllerTest underTest = new EmployeeControllerTest();
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(underTest.callArgumentInstance(file));
    }


    @Test
    public void
    shouldInvokeSaveEmployeeOnTheServiceWhileSavingTheEmployee() {

        EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        EmployeeController employeeController = new EmployeeController(mock);
        Employee employee = new Employee();
        employeeController.saveEmployee(employee);
        Mockito.verify(mock).saveEmployee(employee);
    }

    /**
     * 参数验证
     */
    @Test
    public void shouldFindEmployeeByEmail() {

        final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        final Employee employee = new Employee();
        PowerMockito.when(mock.findEmployeeByEmail(Mockito.startsWith("deep"))).thenReturn(employee);
        final EmployeeController employeeController = new EmployeeController(mock);
        assertSame(employee, employeeController.findEmployeeByEmail("deep@gitshah.com"));
        assertSame(employee, employeeController.findEmployeeByEmail("deep@packtpub.com"));
        assertNull(employeeController.findEmployeeByEmail("noreply@packtpub.com"));
    }

    /**
     * 参数验证
     */
    @Test
    public void shouldReturnNullIfNoEmployeeFoundByEmail() {

        final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        PowerMockito.when(mock.findEmployeeByEmail(Mockito.anyString())).thenReturn(null);
        final EmployeeController employeeController = new EmployeeController(mock);
        assertNull(employeeController.findEmployeeByEmail("deep@gitshah.com"));
        assertNull(employeeController.findEmployeeByEmail("deep@packtpub.com"));
        assertNull(employeeController.findEmployeeByEmail("noreply@packtpub.com"));
    }

    /**
     * 回答(Answer)
     */
    @Test
    public void shouldFindEmployeeByEmailUsingTheAnswerInterface() {

        final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
        final Employee employee = new Employee();

        PowerMockito.when(mock.findEmployeeByEmail(Mockito.anyString())).then(new Answer<Employee>() {

            public Employee answer(InvocationOnMock invocation) throws Throwable {
                final String email = (String) invocation.getArguments()[0];
                if (email == null) return null;
                if (email.startsWith("deep")) return employee;
                if (email.endsWith("packtpub.com")) return employee;
                return null;
            }
        });

        final EmployeeController employeeController = new EmployeeController(mock);
        assertSame(employee, employeeController.findEmployeeByEmail("deep@gitshah.com"));
        assertSame(employee, employeeController.findEmployeeByEmail("deep@packtpub.com"));
        assertNull(employeeController.findEmployeeByEmail("hello@world.com"));
    }

    /**
     * 回答(Answer)
     */
    @Test
    public void shouldReturnCountOfEmployeesFromTheServiceWithDefaultAnswer() {

        EmployeeService mock = PowerMockito.mock(EmployeeService.class, new Answer() {

            public Object answer(InvocationOnMock invocation) {
                return 10;
            }
        });

        EmployeeController employeeController = new EmployeeController(mock);
        assertEquals(12, employeeController.getProjectedEmployeeCount());
    }
}

class Employee {
}

class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    public int getProjectedEmployeeCount() {

        final int actualEmployeeCount = employeeService.getEmployeeCount();
        return (int)Math.ceil(actualEmployeeCount * 1.2);
    }

    public void saveEmployee(Employee employee) {

        employeeService.saveEmployee(employee);
    }

    public Employee findEmployeeByEmail(String email) {
        return employeeService.findEmployeeByEmail(email);
    }

    public boolean isEmployeeEmailAlreadyTaken(String email) {
        Employee employee = new Employee();
        return employeeService.employeeExists(employee);
    }
}

class EmployeeService {

    public int getEmployeeCount() {
        throw new UnsupportedOperationException();
    }

    public void saveEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

    public Employee findEmployeeByEmail(String email) {
        throw new UnsupportedOperationException();
    }

    public boolean employeeExists(Employee employee) {
        throw new UnsupportedOperationException();
    }
}