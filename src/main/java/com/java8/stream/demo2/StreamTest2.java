package com.java8.stream.demo2;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-1 9:54
 * @description：
 * @modified By：
 * @version:
 */
public class StreamTest2 {

    public List<Employee> getAllEmployees() {
        List<Employee> lists = new ArrayList<>();
        Employee e1 = new Employee("大壮", "上海公司", "研发一部", 28, 3000);
        Employee e2 = new Employee("二牛", "上海公司", "研发一部", 24, 2000);
        Employee e3 = new Employee("铁柱", "上海公司", "研发二部", 34, 5000);
        Employee e4 = new Employee("翠花", "南京公司", "测试一部", 27, 3000);
        Employee e5 = new Employee("玲玲", "南京公司", "测试二部", 31, 4000);
        lists.add(e1);
        lists.add(e2);
        lists.add(e3);
        lists.add(e4);
        lists.add(e5);
        return lists;
    }


    //现有集团内所有人员列表，需要从中筛选出上海子公司的全部人员
    @Test
    public void filterEmployeesByCompany() {
        List<Employee> collect = getAllEmployees().stream().filter(e -> "上海公司".equals(e.getSubCompany())).collect(Collectors.toList());
        System.out.println(collect);
    }

    //现有集团内所有人员列表，需要从中筛选出上海子公司的全部人员，并按照部门进行分组
    @Test
    public void filterEmployeesThenGroup() {
        Map<String, List<Employee>> collect = getAllEmployees().stream().filter(employee -> "上海公司".equals(employee.getSubCompany())).collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(collect);
    }

    //计算上海子公司每个月需要支付的员工总工资
    @Test
    public void calculateSum() {
        Integer collect = getAllEmployees().stream().filter(employee -> "上海公司".equals(employee.getSubCompany())).collect(Collectors.summingInt(Employee::getSalary));
        System.out.println(collect);
    }

    //现在需要知道上海子公司里面工资最高的员工信息
    @Test
    public void findHighestSalaryEmployee() {
        Optional<Employee> collect = getAllEmployees().stream().filter(employee -> "上海公司".equals(employee.getSubCompany())).max(Comparator.comparingInt(Employee::getSalary));
        System.out.println(collect.get());
    }

    // 按照子公司分组，并统计每个子公司的员工数
    @Test
    public void groupAndCaculate() {
        Map<String, Long> collect = getAllEmployees().stream().collect(Collectors.groupingBy(employee -> employee.getSubCompany(), Collectors.counting()));
        System.out.println(collect);
    }

    //统计上海公司和非上海公司的员工总数, true表示是上海公司，false表示非上海公司
    @Test
    public void partitionByCompanyAndDepartment() {
        Map<Boolean, Long> collect = getAllEmployees().stream().collect(Collectors.partitioningBy(e -> "上海公司".equals(e.getSubCompany()), Collectors.counting()));
        System.out.println(collect);
    }

    //现有整个集团全体员工的列表，需要统计各子公司内各部门下的员工人数。
    @Test
    public void groupByCompanyAndDepartment() {
        Map<String, Map<String, Long>> collect = getAllEmployees().stream().collect(Collectors.groupingBy(e -> e.getSubCompany(), Collectors.groupingBy(e -> e.getDepartment(), Collectors.counting())));
        System.out.println(collect);
    }

    //给定集团所有员工列表，找出上海公司中工资最高的员工。
    @Test
    public void findHighestSalaryEmployee2() {
        Employee collect = getAllEmployees().stream().filter(e -> "上海公司".equals(e.getSubCompany())).collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)), Optional::get));
        System.out.println(collect);
    }
}

