package com.thread.concurrent.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference也是java.util.concurrent包下的类，跟AtomicInteger等是一样的，也是基于CAS无锁理论实现的，
 * 但是不同的是 AtomicReference 是操控多个属性的原子性的并发类
 * <p>
 * AtomicReference类提供了一个可以原子读写的对象引用变量。 原子意味着尝试更改相同AtomicReference的多个线程
 * （例如，使用比较和交换操作）不会使AtomicReference最终达到不一致的状态。
 * <p>
 *
 * @author ：yanlu.myl
 * @date ：Created in 2022-7-5 15:56
 * @description：
 * @modified By：
 * @version:
 */
public class AtomRefTest {
    public final static AtomicReference<Student> atomicStudent = new AtomicReference<Student>();

    public static void main(String[] args) {
        final Student student1 = new Student("a", 1);
        final Student student2 = new Student("b", 2);

        //初始值为student1对象
        atomicStudent.set(student1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 人为构造休眠，虽然初始化是学生1的信息；但是可能某个线程睡醒之后，原子引用指向的对象已经变成了学生2的信息。
                try {
                    Thread.sleep(Math.abs((int) Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //预期值 student1和 当前值（上面的atomicStudent.set(student1);）相等时候 赋予student2新的值
                Student old = atomicStudent.get();
                System.out.println(Thread.currentThread().getId() + " before update ,studentOld:" + old.toString());
                if (atomicStudent.compareAndSet(student1, student2)) {
                    Student student = atomicStudent.get();
                    System.out.println(Thread.currentThread().getId() + " Change value success! student:" + student.toString());
                } else {
                    Student student = atomicStudent.get();
                    System.out.println(Thread.currentThread().getId() + " Failed,student:" + student.toString());
                }
            }).start();
        }
    }
}

/**
 * 业务场景模拟
 * 序列需要自增并且时间需要更新成最新的时间戳
 */
@Data
class Student {

    private String name;

    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}