package com.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @Author: AngelMa
 * @Description: stream 常用用法
 * @link: [从入门到入土：Lambda完整学习指南](https://www.cnblogs.com/javazhiyin/p/12009464.html)
 * @Date: Created on 2021/6/5 11:37 上午
 * @Modified By:
 */
public class StreamTest {

    /**
     * 过滤
     */
    @Test
    public void testStream09() {
        //使用IntStream遍历快速初始化一批值
        List<Student> students = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new Student("张" + i, i * 3))
            .collect(toList());
        //过滤出分数大于500的并输出
        students.stream()
            .filter(student -> student.getScore() > 10)
            .forEach(System.out::println);
    }

    /**
     * 求和 这里不可给null使用filter过滤掉，否则全为null的情况，会报空指针异常
     */
    @Test
    public void testStream10() {
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
    public void testStream11() {
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
    public void testStream12_reduce() {
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
    public void testStream13_reduce() {
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
    public void testStream14() {
        ArrayList<Student> students = new ArrayList<Student>() {{
            add(new Student("张3", 100, 1));
            add(new Student("张4", 100, 2));
            add(new Student("张5", 100, 2));
            add(new Student("张6", 100, 3));
        }};
        Map<Integer, List<Student>> collect = students.stream()
            .collect(groupingBy(Student::getCls));
        System.out.println(collect);
    }

    /**
     * 按照班级计算平均分
     */
    @Test
    public void testStream15() {
        ArrayList<Student> students = new ArrayList<Student>() {{
            add(new Student("张3", 100, 1));
            add(new Student("张4", 80, 2));
            add(new Student("张5", 100, 2));
            add(new Student("张6", 70, 3));
        }};
        Map<Integer, DoubleSummaryStatistics> collect = students.stream()
            .collect(groupingBy(Student::getCls, Collectors.summarizingDouble(Student::getScore)));
        collect.forEach(
            (key, value) -> System.out.println("班级：" + key + "，平均分：" + value.getAverage()));

    }

    /**
     * 测试:对象::实例方法
     */
    @Test
    public void testMethodRef1() {
        Student stu1 = new Student("张三", 100);
        Supplier s1 = () -> stu1.getName();
        System.out.println(s1.get());
    }

    /**
     * 测试：类::静态方法
     */
    @Test
    public void testMethodRef2() {
        BiFunction<Double, Double, Double> bi = (x, y) -> Math.max(x, y);
        System.out.println(bi.apply(10d, 20d));

        BiFunction<Double, Double, Double> bi2 = Math::max;
        System.out.println(bi2.apply(10d, 20d));
    }

    /**
     * 测试：类::静态方法
     */
    @Test
    public void testMethodRef3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(3, 9));

        System.out.println("-------------------------------------");
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(3, 9));
    }

    /**
     * 测试: 类::实例方法
     */
    @Test
    public void testMethodRef4() {
        BiPredicate<String, String> bip = String::equals;
        BiPredicate<String, String> bip2 = (x, y) -> x.equals(y);
        System.out.println(bip.test("1", "2"));

        Student zhangsan = new Student("张三", 100);
        StudentInterface<Student> sti = (student, name, score, cls) -> student.modify(name, score,
            cls);
        System.out.println(sti.modify(zhangsan, "lisi", 100, 2));
        // 类::实例方法
        Student lisi = new Student("李四", 100);
        StudentInterface<Student> sti2 = Student::modify;
        System.out.println(sti2.modify(lisi, "lisi", 100, 2));

    }

    /**
     * 构造函数 无参的构造方法就是类::实例方法模型
     */
    @Test
    public void testMethodRef5() {
        Supplier<Student> studentSupplier = Student::new;
        System.out.println(studentSupplier.get());

        //Function<String, Student> f1 = (name) -> new Student(name);
        Function<String, Student> f1 = Student::new;
        System.out.println(f1.apply("zhangsan"));

        //BiFunction<String, Integer, Student> bf = (name, score) -> new Student(name, score);
        BiFunction<String, Integer, Student> bf = Student::new;
        System.out.println(bf.apply("lisi", 100));
    }

    /**
     * 作用是删除容器中所有满足filter指定条件的元素，其中Predicate是一个函数接口，里面只有一个待实现方法boolean test(T t)。
     */
    @Test
    public void testLam1() {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        integers.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 3 == 0;
            }
        });
        System.out.println(integers);

        //lambda 的写法
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        list.removeIf(s -> s % 3 == 0);
        System.out.println(list);
    }

    /**
     * 排序
     */
    @Test
    public void testLam2() {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 3, 5, 2, 9));
        Collections.sort(arrayList, (a, b) -> a - b);
        System.out.println(arrayList);
    }

    /**
     * map :forEach操作
     */
    @Test
    public void testLam3() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "我");
        map.put(2, "拒绝");
        map.put(3, "996");
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                System.out.println("key:" + integer + ";value:" + s);
            }
        });
        map.forEach((k, v) -> System.out.println("key:" + k + ";value:" + v));
    }

    /**
     * replaceAll(BiFunction function) 作用是对Map中的每个映射执行function指定的操作， 并用function的执行结果替换原来的value
     */
    @Test
    public void testLam4() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "我");
        map.put(2, "拒绝");
        map.put(3, "996");
        /*map.replaceAll(new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) {
                if (integer == 1) {
                    s = "我们";
                }
                return s;
            }
        });*/
        //lambda 写法
        map.replaceAll((k, v) -> {
            if (k == 1) {
                v = "我们";
            }
            return v;
        });
        map.forEach((k, v) -> System.out.println(String.format("key: %s, value %s", k, v)));
    }

    /**
     * 函数原型为Stream<T> distinct()，作用是返回一个去除重复元素之后的Stream。
     */
    @Test
    public void testStream16_Distinct() {
        Stream<String> i = Stream.of("I", "love", "you", "Java", "you");
        i.distinct()
            .forEach(System.out::print);
    }

    /**
     * 排序函数有两个，一个是用自然顺序排序，一个是使用自定义比较器排序， 函数原型分别为Stream<T>　sorted()和Stream<T>　sorted(Comparator
     * comparator)。
     */
    @Test
    public void testStream17_Sort() {
        Stream<String> i = Stream.of("I", "love", "you", "too", "java");
        //i.sorted().forEach(System.out::print);
        i.sorted((x, y) -> x.length() - y.length())
            .forEach(System.out::println);
    }

    /**
     * 将Stream规约成List 不借助：Collectors.toCollection
     */
    @Test
    public void testStream01() {
        Stream<String> stream = Stream.of("I", "love", "Collector");
        List<String> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);// 方式1
        // List<String> list = stream.collect(Collectors.toList());// 方式2
        System.out.println(list);
    }

    /**
     * stream 转 list/set 人为指定容器的实际类型，这个需求可通过 Collectors.toCollection(Supplier<C>
     * collectionFactory)方法完成。
     */
    @Test
    public void testStream02() {
        // 使用toCollection()指定规约容器的类型
        Stream<String> stream = Stream.of("I", "love", "Collector");
        ArrayList<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(arrayList);
        // HashSet<String> hashSet = stream.collect(Collectors.toCollection(HashSet::new));
        // System.out.println(hashSet);
    }

    /**
     * stream 转 map
     */
    @Test
    public void testStream03() {
        // 使用toMap()统计字符长度
        Stream<String> stream = Stream.of("I", "love", "Collector");
        List<String> list = stream.collect(toList());// 方式2
        Map<String, Integer> map = list.stream()
            .collect(Collectors.toMap(Function.identity(), str -> str.length()));
        System.out.println(map);
    }

    /**
     * 使用partitioningBy()生成的收集器，这种情况适用于将Stream中的元素依据某个二值逻辑（满足条件，或不满足）分成互补相交的两部分，比如男女性别、成绩及格与否等。
     * 下列代码展示将字符列表分成长度大于2或不大于2的两部分。
     */

    @Test
    public void testStream04() {
        // 对字符串列表进行分组
        Stream<String> stream = Stream.of("I", "love", "Collector");
        List<String> list = stream.collect(toList());
        Map<Boolean, List<String>> listMap = list.stream()
            .collect(Collectors.partitioningBy(str -> str.length() > 2));
        System.out.println(listMap);
    }

    /**
     * stream 转 map,分组 使用groupingBy()生成的收集器，这是比较灵活的一种情况。跟SQL中的group by语句类似，这里的groupingBy()也是按照某个属性对数据进行分组，属性相同的元素会被对应到Map的同一个key上。
     * 下列代码展示将字符列表按照字符长度进行分组。
     */
    @Test
    public void testStream05() {
        // 按照长度对字符串列表进行分组
        Stream<String> stream = Stream.of("I", "love", "Collector", "you", "Java");
        List<String> list = stream.collect(toList());// 方式2
        Map<Integer, List<String>> listMap = list.stream()
            .collect(groupingBy(String::length));
        System.out.println(listMap);
    }

    /**
     * 先分组再统计 增强版的groupingBy()允许我们对元素分组之后再执行某种运算，比如求和、计数、平均值、类型转换等。 这种先将元素分组的收集器叫做上游收集器，之后执行其他运算的收集器叫做下游收集器(downstream
     * Collector)。
     */
    @Test
    public void testStream06() {
        // 对字符串列表进行分组，并统计每组元素的个数
        Stream<String> stream = Stream.of("I", "love", "Collector", "you", "Java");
        List<String> list = stream.collect(toList());// 方式2
        Map<Integer, Long> listMap = list.stream()
            .collect(groupingBy(String::length, Collectors.counting()));
        System.out.println(listMap);
    }

    /**
     * 考虑将员工按照部门分组的场景，如果我们想得到每个员工的名字（字符串），而不是一个个Employee对象，可通过如下方式做到： 下游收集器还可以包含更下游的收集器
     */
    @Test
    public void testStream07() {
        ArrayList<Student> students = new ArrayList<Student>() {{
            add(new Student("张3", 100, 1));
            add(new Student("张4", 80, 2));
            add(new Student("张5", 100, 2));
            add(new Student("张6", 70, 3));
        }};
        // 按照部门对员工分组，并只保留员工的名字
        Map<Integer, List<String>> byDept = students.stream()
            .collect(
                Collectors.groupingBy(Student::getCls, Collectors.mapping(Student::getName,// 下游收集器
                    Collectors.toList())));// 更下游的收集器
        byDept.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    /**
     * 字符串拼接时使用Collectors.joining()生成的收集器，从此告别for循环。Collectors.joining()方法有三种重写形式，分别对应三种不同的拼接方式。
     */
    @Test
    public void testStream08() {
        // 使用Collectors.joining()拼接字符串
        Stream<String> stream = Stream.of("I", "love", "Collector");
        // String joined = stream.collect(Collectors.joining());// "IloveCollector"
        // String joined = stream.collect(Collectors.joining(","));// "I,love,Collector"
        String joined = stream.collect(Collectors.joining(",", "{", "}"));// "{I,love,Collector}
        System.out.println(joined);
    }
}
