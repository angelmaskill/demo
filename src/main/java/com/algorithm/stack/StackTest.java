package com.algorithm.stack;

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
        stack.push(0);
        stack.push(1);
        stack.showStack("插入部分数据之后的栈：");
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.showStack("插满数据之后的栈：");
        stack.showStack("弹出数据：");
        while (stack.size() > 0) {
            stack.pop();
        }
        stack.showStack("最后栈为：");
    }
}

class Stack {
    // top 表示栈顶，初始值是0
    private int top = 0;
    private Integer[] stackArray;
    private int size;

    public int size() {
        return top;
    }

    public Stack(int size) {
        this.size = size;
        stackArray = new Integer[size];
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(Integer value) {
        if (!isFull()) {
            stackArray[top++] = value;
        }
    }

    /**
     * 出栈
     */
    public void pop() {
        if (!isEmpty()) {
            System.out.printf("the index %d value %d is pop out!\n", top - 1,
                stackArray[top - 1].intValue());
            stackArray[top - 1] = null;
            top--;
        }
    }

    public boolean isFull() {
        if (top == size) {
            System.out.println("the stack is full!");
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (top == 0) {
            System.out.println("the stack is empty!");
            return true;
        }
        return false;
    }

    public void showStack(String desc) {
        System.out.println(desc);
        if (!isEmpty()) {
            for (int i = stackArray.length - 1; i >= 0; i--) {
                if (stackArray[i] != null) {
                    System.out.printf("the %d index value is %d \n", i, stackArray[i]);
                }
            }
        }
    }
}