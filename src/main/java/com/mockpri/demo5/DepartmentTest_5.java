package com.mockpri.demo5;

/**
 * @Author: yanlu.myl
 * @Description: 查看封装内容
 * @Date: Created on 2018/8/26 17:55
 * @Modified By:
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Whitebox.getInternalState(department, “employees”)类似堆栈，查看变量的值。
 * Whitebox.setInternalState(department, “employees”, employees)设置变量的值。
 * Whitebox.invokeMethod(department, “updateMaxSalaryOffered”)调用方法。
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Department.class)
@SuppressStaticInitializationFor("BaseEntity")
public class DepartmentTest_5 {

    @Test
    public void shouldSuppressTheBaseConstructorOfDepartment() {
        PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
        assertEquals(10, new Department(10).getDepartmentId());
    }

    @Test
    public void shouldSuppressThePerformAuditMethodOfBaseEntity() {
        PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
        PowerMockito.suppress(PowerMockito.method(BaseEntity.class, "performAudit", String.class));
        final Department department = new Department(18);
        department.setName("Mocking with PowerMock");
        assertEquals("Mocking with PowerMock", department.getName());
    }

    @Test
    public void shouldSuppressTheInitializerForBaseEntity() {

        PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
        assertNotNull(new Department(18));
    }
}

class Department extends BaseEntity {

    private int departmentId;
    private String name;

    public Department(int departmentId) {
        super();
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
        super.performAudit(this.name);
    }

    protected void performAudit(String auditInformation) {
        throw new UnsupportedOperationException();
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public Object getName() {
        return name;
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

class BaseEntity {

    static {
        String x = null;
        x.toString();
    }

    public BaseEntity() {
        throw new UnsupportedOperationException();
    }

    protected void performAudit(String auditInformation) {
        throw new UnsupportedOperationException();
    }
}