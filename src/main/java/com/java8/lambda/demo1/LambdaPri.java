package com.java8.lambda.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.Data;
import org.junit.Test;

/**
 * @ClassName LambdaPri
 * @Description lambda 表达式练习
 * @Author yanlu.myl
 * @Date 2021-05-31 11:01
 */
public class LambdaPri {

    @Test
    public void testThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java8");
            }
        }).start();
        //java8 使用lambda表达式
        new Thread(() -> System.out.println("in java8")).start();
    }

    @Test
    public void testCollection() {
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API");
        //Java 8 之前:
        for (String feature : features) {
            System.out.println(feature);
        }
        //Java 8使用lambda表达式:
        features.forEach(str -> System.out.println(str));
    }

    @Test
    public void testFilter() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("words which start with j:");
        // 使用lambda 来过滤单词
        filter2(languages, str -> ((String)str).startsWith("J"));
        // 使用lambda+stream来过滤单词

    }

    @Test
    public void testLambdaObj() {
        LambdaObj lambdaObj = new LambdaObj();
        List<Integer> arrayList = Arrays.asList(1, 2, 3, 4, 5);
        lambdaObj.setList(arrayList);
        lambdaObj.runByData(i -> (Integer)i + 1);
    }

    private void filter(List<String> names, Predicate condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    //java 8 还引入了Stream类
    private void filter2(List names, Predicate condition) {
        names.stream()
            .filter((name) -> (condition.test(name)))
            .forEach((name) -> {
                System.out.println(name + " ");
            });
    }

    //测试消费者
    @Test
    public void testConsumer() {
        rundata("张三", str -> System.out.println("hello! " + str));
    }

    private void rundata(String str, Consumer<String> consumer) {
        consumer.accept(str);
    }

    @Test
    //生产型接口
    public void testSupper() {
        List<Integer> arrayList = wrapNumList(10, () -> (int)(Math.random() * 100));
        arrayList.forEach(System.out::println);
    }

    //需求：产生指定个数的整数，并放入集合中
    private ArrayList<Integer> wrapNumList(int sum, Supplier<Integer> supplier) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            arrayList.add(supplier.get());
        }
        return arrayList;
    }

    //测试function
    public void method1() {
        Function<Numbers, Integer> test1 = i -> i.getN1() - i.getN2();
        Function<Numbers, Integer> test2 = i -> i.getN1() * i.getN2();
        System.out.println(calculate(test1, 111, 5));
        System.out.println(calculate(test2, 111, 5));

        Function<Integer, Integer> fun1 = n -> n * 2;
        Function<Integer, Integer> fun2 = n -> n * n;

        //andThen 先用自己，然后then再用其它
        System.out.println(fun1.andThen(fun2).apply(3));//36
        //compose 先调用其它在用自己
        System.out.println(fun1.compose(fun2).apply(3));//18
    }

    private Integer calculate(Function<Numbers, Integer> test, Integer number1, Integer number2) {
        Numbers n = new Numbers();
        n.setN1(number1);
        n.setN2(number2);
        return test.apply(n);
    }
}
