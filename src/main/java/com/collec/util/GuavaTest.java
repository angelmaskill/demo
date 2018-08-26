package com.collec.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Ordering;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;
import com.google.common.primitives.Ints;
import com.sun.istack.internal.Nullable;
  
/** 
 * Created by shiwenxue on 16/7/13. 
 */  
public class GuavaTest {  
  
    /** 
     * Optional傻瓜式防护类 
     */  
    public void optionalTest() {  
  
        // 创建指定引用的Optional实例，若引用为null则快速失败  
        Optional<Integer> possible1 = Optional.of(5);  
  
        // 创建指定引用的Optional实例，若引用为null则表示缺失  
        Optional<Integer> possible = Optional.fromNullable(5);  
  
        // 判断引用是否缺失  
        boolean present = possible.isPresent();  
  
        // 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException  
        Integer num3 = possible.get();  
  
        // or:若引用缺失,返回or(T)中的T,否则返回引用对象  
        Integer num2 = possible.or(6);  
  
        // orNull:若引用缺失,返回or(T)中的T,否则返回引用对象  
        Integer num = possible.orNull();  
  
        // 返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。  
        Set<Integer> set = possible.asSet();  
  
        System.out.println(set.size());  
        System.out.println(num2);  
    }  
  
    /** 
     * check 静态检查函数 
     */  
    public void checkArgTest() {  
  
        try {  
            String str1 = "sss";  
            String str2 = null;  
  
            // 检查参数  
            checkArgument(!"".equals(str1), "str is empty");  
  
            // 用来检查对象是否null。  
            checkNotNull(str2, "str is null");  
  
            // 用来检查对象的某些状态。  
            checkState(!"".equals(str1));  
  
            // 检查指针是否越界  
            // checkElementIndex(int index, int size)  
  
            // 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*  
            // checkPositionIndexes(int start, int end, int size)  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * ComparisonChain 比较链 
     */  
    public int compareTo(A a1, A a2) {  
        return ComparisonChain.start()  
                .compare(a1.getGender(), a2.getGender())  
                .compare(a1.getName(), a2.getName())  
                .result();  
    }  
  
    /** 
     * Ordering 排序器 
     */  
    public void orderTest(List<A> aList) {  
  
        // 多重排序组合,从后往前;按照age倒序  
        Ordering<A> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<A, Integer>() {  
            public Integer apply(A a) {  
                return a.getAge();  
            }  
        });  
  
        // 字符串长度排序:倒序  
        Ordering<String> byLengthOrdering = new Ordering<String>() {  
            public int compare(String left, String right) {  
                return Ints.compare(left.length(), right.length());  
            }  
        };  
  
        // 字符串长度排序-->取名字排序  
        Ordering<A> byLengthOrdering2 = new Ordering<A>() {  
            public int compare(A left, A right) {  
                return Ints.compare(left.getName().length(), right.getName().length());  
            }  
        };  
  
        // 获取排序结果  
        List<A> result = byLengthOrdering2.greatestOf(aList.iterator(), aList.size());  
  
        // 获取排序后的前2个结果  
        List<A> result2 = ordering.greatestOf(aList.iterator(), 2);  
  
        // 返回排序结果最小的那个(按名字)  
        A aa = byLengthOrdering2.min(aList.iterator());  
  
        // 判断是否准确排序  
        // boolean is = ordering.isOrdered((Iterable<A>) aList.iterator());  
    }  
  
    /** 
     * ImmutableList:不可变集合 
     */  
    public void immutableCollectionTest() {  
        List<String> temp = new ArrayList<>();  
        temp.add("m");  
        temp.add("n");  
  
        // 获取不可变集合,下面三种效果相似  
        List<String> list = ImmutableList.<String>builder().addAll(temp).build();  
        List<String> list2 = ImmutableList.copyOf(temp);  
        List<String> list3 = ImmutableList.of("a", "b");  
  
        // 若在执行下面操作不允许  
        // list3.add("c");  
    }  
  
    /** 
     * 新型集合扩展 
     */  
    public void collectionTest() {  
        // 1. Multiset:可以添加相同元素,提供count计数方案  
        Multiset<String> mutiSet = HashMultiset.create();  
        mutiSet.add("swx");  
        mutiSet.add("swx1");  
        mutiSet.add("swx2");  
        mutiSet.add("swx1");  
        mutiSet.add("swx3", 5);  
        mutiSet.setCount("swx4", 5);  
  
        // count(T):计数  
        int count = mutiSet.count("swx5");  
  
        // elementSet():返回不重复的元素  
        Set<String> set = mutiSet.elementSet();  
  
  
        // 2.Multimap:一key对多value  
        Multimap<String, String> multiMap = HashMultimap.create();  
        multiMap.put("key1", "key1");  
        multiMap.put("key1", "key11");  
        multiMap.put("key1", "key111");  
        multiMap.put("key2", "key2");  
  
        // asMap():返回Map<K,Collection<V>>形式的视图  
        Map<String, Collection<String>> mmap = multiMap.asMap();  
  
        // keySet():返回不同的key  
        Set<String> keySet = multiMap.keySet();  
  
        // keys():用Multiset表示Multimap中的所有键，每个键重复出现的次数等于它映射的值的个数  
        Multiset<String> keyMutiliSet = multiMap.keys();  
  
        // values():用一个”扁平”的Collection<V>包含Multimap中的所有值  
        Collection<String> values = multiMap.values();  
  
        // 移除操作  
        multiMap.remove("key1", "key1");  
        multiMap.removeAll("key1");  
  
  
        // 3.BiMap双向map  
        HashBiMap<Integer, String> isMap = HashBiMap.create();  
        isMap.put(1, "str1");  
        isMap.put(2, "str2");  
  
        // inverse():倒转key:value  
        BiMap<String, Integer> newMap = isMap.inverse();  
  
    }  
  
    /** 
     * Lists、Maps等集合工具 
     */  
    public void newCollectionUtilTest() {  
        // 初始化一个集合  
        List<String> list = Lists.newArrayList();  
        Map<String, String> map = Maps.newHashMap();  
        Set<String> set = Sets.newHashSet();  
        list = Lists.newArrayList("1", "2", "3");  
  
        // 将list按指定大小分隔  
        List<List<String>> newLists = Lists.partition(list, 1);  
        // 翻转list元素  
        list = Lists.reverse(list);  
  
        // 对不可变集合进行反转  
        list = ImmutableList.of("4", "5", "6");  
        ImmutableList t = ImmutableList.copyOf(list);  
        ImmutableList<String> iList = t.reverse();  
  
        List<A> aList = initData();  
  
        // 函数式编程  
        // 提取所有对象主键  
        Set<Integer> itemIdSet = FluentIterable.from(aList).transform(new Function<A, Integer>() {  
            @Override  
            public Integer apply(A a) {  
                return a.getAge();  
            }  
        }).toSet();  
  
        // 将集合按照主键映射成map,场景:主键唯一  
        Map<String, A> aMap = Maps.uniqueIndex(aList, new Function<A, String>() {  
            @Nullable  
            @Override  
            public String apply(A a) {  
                return a.getName();  
            }  
        });  
  
        // 将集合按照主键映射成map,场景:主键不唯一  
        // Multimaps.index(Iterable, Function);  
  
        // 比较两个Map  
        MapDifference diff = Maps.difference(new HashMap(), new HashMap());  
        diff.entriesInCommon();// 两个Map中都有的映射项，包括匹配的键与值  
        diff.entriesDiffering();// 键相同但是值不同值映射项。返回的Map的值类型  
        diff.entriesOnlyOnLeft();// 键只存在于左边Map的映射项  
        diff.entriesOnlyOnRight();// 键只存在于右边Map的映射项  
  
        // 返回两个multiSet的交集  
        Multiset multiSet = Multisets.intersection(HashMultiset.create(), HashMultiset.create());  
  
        // map反转操作  
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();  
        multimap.putAll("b", Ints.asList(2, 4, 6));  
        multimap.putAll("a", Ints.asList(4, 2, 1));  
        multimap.putAll("c", Ints.asList(2, 5, 3));  
        TreeMultimap<Integer, String> inverse = Multimaps.invertFrom(multimap, TreeMultimap.<Integer, String>create());  
        //1 => {"a"},2 => {"a", "b", "c"} ,3 => {"c"},4 => {"a", "b"},5 => {"c"}, 6 => {"b"}  
  
        // forMap(Map):Map包装成SetMultimap  
        Map<String, Integer> map2 = ImmutableMap.of("a", 1, "b", 1, "c", 2);  
        SetMultimap<String, String> multimap2 = Multimaps.forMap(map);  
        // multimap：["a" => {1}, "b" => {1}, "c" => {2}]  
        Multimap<Integer, String> inverse2 = Multimaps.invertFrom(multimap, HashMultimap.<Integer, String>create());  
        // inverse：[1 => {"a","b"}, 2 => {"c"}]  
  
        // 自定义Multimap的方法允许你指定Multimap中的特定实现  
//        ListMultimap<String, Integer> myMultimap = Multimaps.newListMultimap(  
//                Maps.<String, Collection>newTreeMap(),  
//                new Supplier<LinkedList>() {  
//                    public LinkedList get() {  
//                        return Lists.newLinkedList();  
//                    }  
//                });  
  
    }  
  
    /** 
     * Forwarding装饰着,加强默认的get、add等行为 
     */  
    class AddLoggingList<E> extends ForwardingList<E> {  
        final List<E> delegate; // backing list  
        AddLoggingList(List<E> delegate) {  
            this.delegate = delegate;  
        }  
  
        @Override  
        protected List<E> delegate() {  
            return delegate;  
        }  
  
        @Override  
        public void add(int index, E elem) {  
            System.out.println("add a elem at index");  
            super.add(index, elem);  
        }  
        @Override  
        public boolean add(E elem) {  
            System.out.println("add a elem");  
            return standardAdd(elem); // 用add(int, E)实现  
        }  
        @Override  
        public boolean addAll(Collection<? extends E> c) {  
            System.out.println("add all elem");  
            return standardAddAll(c); // 用add实现  
        }  
    }  
  
    /** 
     * Forwarding装饰者,加强默认的get、add等行为测试 
     */  
    public void forwardingTest(){  
        List<String> list = new ArrayList<>();  
        AddLoggingList<String> listWrap = new AddLoggingList(list);  
        listWrap.add("1");  
        System.out.println(list.size());  
    }  
  
    /** 
     * PeekingIterator装饰者,事先窥视[peek()]到下一次调用next()返回的元素。 
     * 复制一个List，并去除连续的重复元素。 
     */  
    public void peekingIteratorTest(){  
        List<String> source = new ArrayList<>();  
        List<String> result = Lists.newArrayList();  
        PeekingIterator<String> iter = Iterators.peekingIterator(source.iterator());  
        while (iter.hasNext()) {  
            String current = iter.next();  
            while (iter.hasNext() && iter.peek().equals(current)) {  
                //跳过重复的元素  
                iter.next();  
            }  
            result.add(current);  
        }  
    }  
  
    
    
    
    public static void main(String args[]) {  
        GuavaTest t = new GuavaTest();  
        t.forwardingTest();  
    }  
  
    public List<A> initData() {  
        A a1 = new A();  
        a1.setAge(22);  
        a1.setGender("sex");  
        a1.setName("swx1");  
  
        A a2 = new A();  
        a2.setAge(20);  
        a2.setGender("sex");  
        a2.setName("swx123");  
  
        A a3 = new A();  
        a3.setAge(25);  
        a3.setGender("sex");  
        a3.setName("swx32");  
  
        List<A> list = new ArrayList<>();  
        list.add(a1);  
        list.add(a2);  
        list.add(a3);  
  
        return list;  
    }  
  
    class A {  
        @Nullable  
        private int age;  
        private String name;  
        private String gender;  
  
        public int getAge() {  
            return age;  
        }  
  
        public void setAge(int age) {  
            this.age = age;  
        }  
  
        public String getName() {  
            return name;  
        }  
  
        public void setName(String name) {  
            this.name = name;  
        }  
  
        public String getGender() {  
            return gender;  
        }  
  
        public void setGender(String gender) {  
            this.gender = gender;  
        }  
    }  
}  