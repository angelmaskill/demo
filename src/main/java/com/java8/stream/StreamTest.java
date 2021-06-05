package com.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @Author: AngelMa
 * @Description: stream 常用用法
 * @Date: Created on 2021/6/5 11:37 上午
 * @Modified By:
 */
public class StreamTest {

    /**
     * 过滤
     */
    @Test
    public void test1() {
        //使用IntStream遍历快速初始化一批值
        List<Student> students = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new Student("张" + i, i * 3))
            .collect(Collectors.toList());
        //过滤出分数大于500的并输出
        students.stream()
            .filter(student -> student.getScore() > 10)
            .forEach(System.out::println);
    }

    /**
     * 求和 这里不可给null使用filter过滤掉，否则全为null的情况，会报空指针异常
     */
    @Test
    public void test2() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, null);
        double sum = integers.stream()
            .map(i -> Objects.isNull(i) ? 0 : i)
            .mapToDouble(i -> i)
            .sum();
        System.out.println(sum);
    }

    /**
     * 汇总统计
     */
    @Test
    public void test3() {
        List<Double> nums = Arrays.asList(1.01, 2.11, 3.23, 4.222, null, 5.6);
        DoubleSummaryStatistics doubleSummaryStatistics = nums.stream()
            .map(i -> Objects.isNull(i) ? 0 : i)
            .mapToDouble(i -> i)
            .summaryStatistics();
        System.out.println("max:\t" + doubleSummaryStatistics.getMax());
        System.out.println("min:\t" + doubleSummaryStatistics.getMin());
        System.out.println("ave:\t" + doubleSummaryStatistics.getAverage());
        System.out.println("cnt:\t" + doubleSummaryStatistics.getCount());
        System.out.println("sum:\t" + doubleSummaryStatistics.getSum());
    }

    //累加
    @Test
    public void reduceTest() {
        Optional accResult = Stream.of(1, 2, 3, 4)
            .reduce((acc, item) -> {
                System.out.println("acc : " + acc);
                acc += item;
                System.out.println("item: " + item);
                System.out.println("acc+ : " + acc);
                System.out.println("--------");
                return acc;
            });
        System.out.println(accResult);
    }

    /**
     * 从 100 开始累加
     */
    @Test
    public void reduceTest2() {
        int accResult = Stream.of(1, 2, 3, 4)
            .reduce(100, (acc, item) -> {
                System.out.println("acc : " + acc);
                acc += item;
                System.out.println("item: " + item);
                System.out.println("acc+ : " + acc);
                System.out.println("--------");
                return acc;
            });
        System.out.println(accResult);
    }

    /**
     * 按照班级分组
     */
    @Test
    public void testGroupBy() {
        ArrayList<Student> students = new ArrayList<Student>() {{
            add(new Student("张3", 100, 1));
            add(new Student("张4", 100, 2));
            add(new Student("张5", 100, 2));
            add(new Student("张6", 100, 3));
        }};
        Map<Integer, List<Student>> collect = students.stream()
            .collect(Collectors.groupingBy(Student::getCls));
        System.out.println(collect);
    }

    /**
     * 按照班级计算平均分
     */
    @Test
    public void test4() {
        ArrayList<Student> students = new ArrayList<Student>() {{
            add(new Student("张3", 100, 1));
            add(new Student("张4", 80, 2));
            add(new Student("张5", 100, 2));
            add(new Student("张6", 70, 3));
        }};
        Map<Integer, DoubleSummaryStatistics> collect = students.stream()
            .collect(Collectors.groupingBy(Student::getCls,
                Collectors.summarizingDouble(Student::getScore)));
        collect.forEach(
            (key, value) -> System.out.println("班级：" + key + "，平均分：" + value.getAverage()));

    }
}
