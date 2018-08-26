package com.collec.demo2;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimaps;

@SuppressWarnings({"unchecked","rawtypes"})
public class TestGroup {
public static void main(String[] args) {
	TestGroup tg = new TestGroup();
	tg.testTest6();
}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@org.junit.Test
	public void testTest6() {
		Person p1 = new Person("a", 10, "男", "china");
		Person p2 = new Person("b", 11, "女", "japan");
		Person p3 = new Person("c", 12, "男", "japan");
		Person p4 = new Person("d", 14, "男", "USA");
		Person p5 = new Person("e", 14, "女", "china");
		List<Person> persons = new ArrayList<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);
		Function<Person, String> sex = new Function<Person, String>() {
			@Override
			public String apply(Person person) {
				return person.getSex();
			}
		};

		ImmutableListMultimap<String, Person> sexList = Multimaps.index(persons.iterator(), sex);

		ImmutableListMultimap ageList = Multimaps.index(persons.iterator(), new Function<Person, Integer>() {
			@Override
			public Integer apply(Person person) {
				return person.getAge();
			}
		});

		ImmutableListMultimap countryList = Multimaps.index(persons.iterator(), new Function<Person, String>() {
			@Override
			public String apply(Person person) {
				return person.getCt();
			}
		});
		System.out.println("按性别分组 = " + sexList);
		System.out.println("按年龄分组 = " + ageList);
		System.out.println("按国家分组 = " + countryList);

        /** 结果
         * 按性别分组 = {男=[Person@44d03877, Person@215750e4, Person@6b7fb9d5], 女=[Person@422b2fec, Person@e818616]}
         * 按年龄分组 = {10=[Person@44d03877], 11=[Person@422b2fec], 12=[Person@215750e4], 14=[Person@6b7fb9d5, Person@e818616]}
         * 按国家分组 = {china=[Person@44d03877, Person@e818616], japan=[Person@422b2fec, Person@215750e4], USA=[Person@6b7fb9d5]}
         */
        //求性别为男的集合
        System.out.println("sexList = " + sexList.get("男"));
        //求年龄为14岁的集合
        System.out.println("ageList = " + ageList.get(14));
        //求国籍日本的集合
        System.out.println("countryList = " + countryList.get("japan"));
        /**
         * 结果
         * sexList = [Person@e818616, Person@789caeb2, Person@769165fa]
         * ageList = [Person@769165fa, Person@43be3ce6]
         * countryList = [Person@598a15ca, Person@789caeb2]
         * 以上结果就是根据返回的map得到的集合数据。
         * 可以把结果用List<Person> 保存。
         * List<Person> personMan = sexList.get("男");
         */
    }

}
