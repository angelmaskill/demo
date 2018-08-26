package com.mockpri.demo4;

/**
 * @Author: yanlu.myl
 * @Description: 查看封装内容
 * @Date: Created on 2018/8/26 17:55
 * @Modified By:
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * Whitebox.getInternalState(department, “employees”)类似堆栈，查看变量的值。
 * Whitebox.setInternalState(department, “employees”, employees)设置变量的值。
 * Whitebox.invokeMethod(department, “updateMaxSalaryOffered”)调用方法。
 */
public class DepartmentTest {
    @Test
    public void shouldVerifyThatNewEmployeeIsAddedToTheDepartment() {

        final Department department = new Department();
        final Employee employee = new Employee();
        department.addEmployee(employee);
        final List<Employee> employees = Whitebox.getInternalState(department, "employees");
        assertTrue(employees.contains(employee));
    }

    @Test
    public void shouldAddNewEmployeeToTheDepartment() {

        final Department department = new Department();
        final Employee employee = new Employee();
        final ArrayList<Employee> employees = new ArrayList<Employee>();
        Whitebox.setInternalState(department, "employees", employees);
        department.addEmployee(employee);
        assertTrue(employees.contains(employee));
    }

    @Test
    public void shouldVerifyThatMaxSalaryOfferedForADepartmentIsCalculatedCorrectly() throws Exception {

        final Department department = new Department();
        final Employee employee1 = new Employee();
        final Employee employee2 = new Employee();
        employee1.setSalary(60000);
        employee2.setSalary(65000);
        //Adding two employees to the test employees list.
        final ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);
        Whitebox.setInternalState(department, "employees", employees);
        Whitebox.invokeMethod(department, "updateMaxSalaryOffered");
        final long maxSalary = Whitebox.getInternalState(department, "maxSalaryOffered");
        assertEquals(65000, maxSalary);
    }
}

class Department {

    private List<Employee> employees = new ArrayList<Employee>();
    private long maxSalaryOffered;

    public void addEmployee(final Employee employee) {
        employees.add(employee);
        updateMaxSalaryOffered();
    }

    /**
     * The private method that keeps track of
     * max salary offered by this department.
     */
    private void updateMaxSalaryOffered() {
        maxSalaryOffered = 0;
        for (Employee employee : employees) {
            if (employee.getSalary() > maxSalaryOffered) {
                maxSalaryOffered = employee.getSalary();
            }
        }
    }
}

class Employee {
    private long salary;

    public long getSalary() {
        return salary;
    }

    public void setSalary(int i) {
        salary = i;
    }
}