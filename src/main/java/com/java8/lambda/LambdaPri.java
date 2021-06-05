package com.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
}
