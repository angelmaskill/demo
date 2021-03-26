package com.annotation.demo3.use;

import com.annotation.demo3.Constraints;
import com.annotation.demo3.DBTable;
import com.annotation.demo3.SQLInteger;
import com.annotation.demo3.SQLString;

@DBTable(name = "MEMBER")
public class Member {
    // 在使用注解过程中，如果有元素是value，并且只有value需要赋值，
    // 则只需要在()中将值写入
    @SQLString(30)
    private String firstName;
    @SQLString(50)
    private String lastName;
    @SQLInteger
    private Integer age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    private String handle;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}