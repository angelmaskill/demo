package com.algorithm.linkedlist;

/**
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/4/3 8:47 上午
 * @Modified By:
 */
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.showStack("插入数据之前的栈：");
        stack.in(0);
        stack.in(1);
        stack.showStack("插入部分数据之后的栈：");
        stack.in(2);
        stack.in(3);
        stack.in(4);
        stack.showStack("插满数据之后的栈：");
        stack.showStack("弹出数据：");
        while (stack.size() > 0) {
            stack.out();
        }
        stack.showStack("最后栈为：");
    }
}

class Stack {
    private int current;
    private Integer[] stackArray;
    private int size;

    public int size() {
        return current;
    }

    public Stack(int size) {
        this.size = size;
        stackArray = new Integer[size];
    }

    public void in(Integer value) {
        if (!isFull()) {
            stackArray[current++] = value;
        }
    }

    public void out() {
        if (!isEmpty()) {
            System.out.printf("the index %d value %d is pop out!\n", current - 1,
                stackArray[current - 1].intValue());
            stackArray[current - 1] = null;
            current--;
        }
    }

    public boolean isFull() {
        if (current == size) {
            System.out.println("the stack is full!");
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (current == 0) {
            System.out.println("the stack is empty!");
            return true;
        }
        return false;
    }

    public void showStack(String desc) {
        System.out.println(desc);
        if (!isEmpty()) {
            for (int i = 0; i < stackArray.length; i++) {
                if (stackArray[i] != null) {
                    System.out.printf("the %d index value is %d \n", i, stackArray[i]);
                }
            }
        }
    }
}