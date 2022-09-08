package com.java8.stream.demo4;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-9-6 17:13
 * @description：
 * @modified By：
 * @version:
 */
public class TestStream {
    /**
     * 【常规方式】
     * 从给定句子中返回单词长度大于5的单词列表，按长度倒序输出，最多返回3个
     *
     * @param sentence 给定的句子，约定非空，且单词之间仅由一个空格分隔
     * @return 倒序输出符合条件的单词列表
     */
    public List<String> sortGetTop3LongWords(@NotNull String sentence) {
        // 先切割句子，获取具体的单词信息
        String[] words = sentence.split(" ");
        List<String> wordList = new ArrayList<>();
        // 循环判断单词的长度，先过滤出符合长度要求的单词
        for (String word : words) {
            if (word.length() > 5) {
                wordList.add(word);
            }
        }
        // 对符合条件的列表按照长度进行排序
        wordList.sort((o1, o2) -> o2.length() - o1.length());
        // 判断list结果长度，如果大于3则截取前三个数据的子list返回
        if (wordList.size() > 3) {
            wordList = wordList.subList(0, 3);
        }
        return wordList;
    }


    /**
     * 【Stream方式】
     * 从给定句子中返回单词长度大于5的单词列表，按长度倒序输出，最多返回3个
     *
     * @param sentence 给定的句子，约定非空，且单词之间仅由一个空格分隔
     * @return 倒序输出符合条件的单词列表
     */
    public List<String> sortGetTop3LongWordsByStream(@NotNull String sentence) {
        return Arrays.stream(sentence.split(" "))
                .filter(word -> word.length() > 5)
                .sorted((o1, o2) -> o2.length() - o1.length())
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * 演示map的用途：一对一转换
     * 有一个字符串ID列表，现在需要将其转为User对象列表。可以使用map来实现：
     */
    @Test
    public void stringToIntMap() {
        List<String> ids = Arrays.asList("205", "105", "308", "469", "627", "193", "111");
        // 使用流操作
        List<User> results = ids.stream()
                .map(id -> {
                    User user = new User(id);
                    return user;
                })
                .collect(Collectors.toList());
        System.out.println(results);
    }

    /**
     * flatMap操作的时候其实是先每个元素处理并返回一个新的Stream，然后将多个Stream展开合并为了一个完整的新的Stream
     * 现有一个句子列表，需要将句子中每个单词都提取出来得到一个所有单词列表。这种情况用map就搞不定了，需要flatMap上场了：
     */
    @Test
    public void stringToIntFlatmap() {
        List<String> sentences = Arrays.asList("hello world", "Jia Gou Wu Dao");
        // 使用流操作
        List<String> results = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println(results);
    }

    @Test
    public void testFlatmap(){
        List<String> words = new ArrayList(){};
        words.add("hello");
        words.add("world");
        List<String> collect = words.stream().flatMap(s -> Arrays.stream(s.split(""))).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * peek和foreach，都可以用于对元素进行遍历然后逐个的进行处理。
     * <p>
     * peek属于中间方法，而foreach属于终止方法。
     * peek只能作为管道中途的一个处理步骤，而没法直接执行得到结果，其后面必须还要有其它终止操作的时候才会被执行；
     * 而foreach作为无返回值的终止方法，则可以直接执行相关操作。
     */
    @Test
    public void testPeekAndforeach() {
        List<String> sentences = Arrays.asList("hello world", "Jia Gou Wu Dao");
        // 演示点1： 仅peek操作，最终不会执行
        System.out.println("----before peek----");
        sentences.stream().peek(sentence -> System.out.println(sentence));
        System.out.println("----after peek----");
        // 演示点2： 仅foreach操作，最终会执行
        System.out.println("----before foreach----");
        sentences.stream().forEach(sentence -> System.out.println(sentence));
        System.out.println("----after foreach----");
        // 演示点3： peek操作后面增加终止操作，peek会执行
        System.out.println("----before peek and count----");
        sentences.stream().peek(sentence -> System.out.println(sentence)).count();
        System.out.println("----after peek and count----");
    }


    /**
     * filter、sorted、distinct、limit
     * 这几个都是常用的Stream的中间操作方法，具体的方法的含义在上面的表格里面有说明。
     * <p>
     * 使用filter过滤掉不符合条件的数据
     * 通过distinct对存量元素进行去重操作
     * 通过map操作将字符串转成整数类型
     * 借助sorted指定按照数字大小正序排列
     * 使用limit截取排在前3位的元素
     * 又一次使用map将id转为Dept对象类型
     * 使用collect终止操作将最终处理后的数据收集到list中
     */
    @Test
    public void testGetTargetUsers() {
        List<String> ids = Arrays.asList("205", "10", "308", "49", "627", "193", "111", "193");
        // 使用流操作
        List<Dept> results = ids.stream()
                .filter(s -> s.length() > 2)
                .distinct()
                .map(Integer::valueOf)
                .sorted(Comparator.comparingInt(o -> o))
                .limit(3)
                .map(id -> new Dept(id))
                .collect(Collectors.toList());
        System.out.println(results);
    }

    /**
     * 简单结果终止方法
     * 按照前面介绍的，终止方法里面像count、max、min、findAny、findFirst、anyMatch、allMatch、nonneMatch
     * 等方法，均属于这里说的简单结果终止方法。所谓简单，指的是其结果形式是数字、布尔值或者Optional对象值等。
     */
    @Test
    public void testSimpleStopOptions() {
        List<String> ids = Arrays.asList("205", "10", "308", "49", "627", "193", "111", "193");
        // 统计stream操作后剩余的元素个数
        System.out.println(ids.stream().filter(s -> s.length() > 2).count());
        // 判断是否有元素值等于205
        System.out.println(ids.stream().filter(s -> s.length() > 2).anyMatch("205"::equals));
        // findFirst操作
        ids.stream().filter(s -> s.length() > 2)
                .findFirst()
                .ifPresent(s -> System.out.println("findFirst:" + s));
    }


    /**
     * 一旦一个Stream被执行了终止操作之后，后续便不可以再读这个流执行其他的操作了，否则会报错
     */
    @Test
    public void testHandleStreamAfterClosed() {
        List<String> ids = Arrays.asList("205", "10", "308", "49", "627", "193", "111", "193");
        Stream<String> stream = ids.stream().filter(s -> s.length() > 2);
        // 统计stream操作后剩余的元素个数
        System.out.println(stream.count());
        System.out.println("-----下面会报错-----");
        // 判断是否有元素值等于205
        try {
            System.out.println(stream.anyMatch("205"::equals));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----上面会报错-----");
    }

    /**
     * collect: 获取一个集合类的结果对象，比如List、Set或者HashMap等。
     * 一个集合类，比如List、Set或者HashMap等
     * StringBuilder对象，支持将多个字符串进行拼接处理并输出拼接后结果
     * 一个可以记录个数或者计算总和的对象（数据批量运算统计）
     */
    @Test
    public void testCollectStopOptions() {
        List<Dept> ids = Arrays.asList(new Dept(17), new Dept(22), new Dept(23));
        // collect成list
        List<Dept> collectList = ids.stream().filter(dept -> dept.getId() > 20)
                .collect(Collectors.toList());
        System.out.println("collectList:" + collectList);
        // collect成Set
        Set<Dept> collectSet = ids.stream().filter(dept -> dept.getId() > 20)
                .collect(Collectors.toSet());
        System.out.println("collectSet:" + collectSet);
        // collect成HashMap，key为id，value为Dept对象
        Map<Integer, Dept> collectMap = ids.stream().filter(dept -> dept.getId() > 20)
                .collect(Collectors.toMap(Dept::getId, dept -> dept));
        System.out.println("collectMap:" + collectMap);
    }

    /**
     * 将一个List或者数组中的值拼接到一个字符串里并以逗号分隔开
     *
     * 通过for循环和StringBuilder去循环拼接，还得考虑下最后一个逗号如何处理的问题，很繁琐:
     */
    @Test
    public void testForJoinStrings() {
        List<String> ids = Arrays.asList("205", "10", "308", "49", "627", "193", "111", "193");
        StringBuilder builder = new StringBuilder();
        for (String id : ids) {
            builder.append(id).append(',');
        }
        // 去掉末尾多拼接的逗号
        builder.deleteCharAt(builder.length() - 1);
        System.out.println("拼接后：" + builder.toString());
    }

    /**
     * 将一个List或者数组中的值拼接到一个字符串里并以逗号分隔开
     * Stream，使用collect实现
     */
    @Test
    public void testCollectJoinStrings() {
        List<String> ids = Arrays.asList("205", "10", "308", "49", "627", "193", "111", "193");
        String joinResult = ids.stream().collect(Collectors.joining(","));
        System.out.println("拼接后：" + joinResult);
    }

    /**
     * 数据批量数学运算
     * 还有一种场景，实际使用的时候可能会比较少，就是使用collect生成数字数据的总和信息，也可以了解下实现方式：
     */
    @Test
    public void testNumberCalculate() {
        List<Integer> ids = Arrays.asList(10, 20, 30, 40, 50);
        // 计算平均值
        Double average = ids.stream().collect(Collectors.averagingInt(value -> value));
        System.out.println("平均值：" + average);
        // 数据统计信息
        IntSummaryStatistics summary = ids.stream().collect(Collectors.summarizingInt(value -> value));
        System.out.println("数据统计信息： " + summary);
    }


    public static void main(String[] args) {
        String str = "hello my favourite beauty!";
        TestStream test = new TestStream();
        //test.sortGetTop3LongWords(str).stream().forEach(a -> System.out.println(a));
        test.sortGetTop3LongWordsByStream(str).stream().forEach(a -> System.out.println(a));
    }
}
