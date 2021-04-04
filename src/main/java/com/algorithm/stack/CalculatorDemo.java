package com.algorithm.stack;

/**
 * @Author: AngelMa
 * @Description: 使用栈来模拟计算器
 * @Date: Created on 2021/4/4 9:40 下午
 * @Modified By:
 */
public class CalculatorDemo {

    public static void main(String[] args) {
        ArrayStack dataArrayStack = new ArrayStack(100);
        ArrayStack operArrayStack = new ArrayStack(100);
        //String expression = "2-1*9-4";
        //String expression = "7*2*2-5+1-5+3-4";
        String expression = "7*2*2-5+1-5-5-3-5*2*2+3-4";
        char[] chars = expression.toCharArray();
        for (char aChar : chars) {
            boolean isDigit = Calculator.isDigit(aChar);
            boolean isOperator = Calculator.isOperator(aChar);
            if (isDigit) {
                // 解析数字如数栈
                dataArrayStack.push(aChar);
            } else if (isOperator) {
                // 解析操作符，入操作符栈
                pushOperator(dataArrayStack, operArrayStack, aChar);
            }
        }

        //获取最终的结果
        getFinalResult(dataArrayStack, operArrayStack);
    }

    private static void pushOperator(ArrayStack dataArrayStack, ArrayStack operArrayStack,
        char aChar) {
        if (operArrayStack.isEmpty()) {
            operArrayStack.push(aChar);
        } else {
            parseAndPushOper(dataArrayStack, operArrayStack, aChar);
        }
    }

    private static void getFinalResult(ArrayStack dataArrayStack, ArrayStack operArrayStack) {
        dataArrayStack.showStack("最后的数栈");
        operArrayStack.showStack("最后的操作符栈");
        while (dataArrayStack.size() > 1) {
            int num1 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
            int num2 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
            char operator = (char)operArrayStack.pop();
            //如果的操作符是-号，当前的是加号，就要把加号变为减号
            Object top = operArrayStack.getTop();
            int calResult = Calculator.cal(num1, num2, operator);
            dataArrayStack.push(calResult);
        }
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
        int finalResult = (int)dataArrayStack.pop();
        System.out.println(finalResult);
    }

    private static boolean parseAndPushOper(ArrayStack dataArrayStack, ArrayStack operArrayStack,
        char aChar) {
        boolean isNeedReCalc = true;
        Object topObj = operArrayStack.getTop();
        if (topObj == null) {
            operArrayStack.push(aChar);
            return false;
        }
        char top = (char)topObj;
        char priorityHigh = Calculator.isPriorityHigh(aChar, top);
        // 当前操作符的优先级小于等于栈中的操作符
        if ('<' == priorityHigh || '=' == priorityHigh) {
            int num1 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
            int num2 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
            char operator = (char)operArrayStack.pop();
            int calResult = Calculator.cal(num1, num2, operator);
            dataArrayStack.push(calResult);
            boolean flag = parseAndPushOper(dataArrayStack, operArrayStack, aChar);
            if (flag) {
                operArrayStack.push(aChar);
            }
        } else {//当前操作符优先级>符号栈顶操作符，直接入栈
            operArrayStack.push(aChar);
        }
        return false;
    }
}

class Calculator {

    /**
     * 计算方法
     *
     * @param num1     操作数 1
     * @param num2     操作数 2
     * @param operator 操作符
     * @return
     */
    public static int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public static int isPriorityHigh(Character oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式只有 +, - , * , /
        }
    }

    /**
     * 判断ch是否为运算符 目前只支持四则运算
     */
    public static boolean isOperator(char ch) {
        switch (ch) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }

    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    public static char isPriorityHigh(char current, char top1) {
        switch (current) {
            case '+':
                if (top1 == '+') { return '='; }
                if (top1 == '-') { return '='; }
                if (top1 == '*') { return '<'; }
                if (top1 == '/') { return '<'; }
            case '-':
                if (top1 == '+') { return '='; }
                if (top1 == '-') { return '='; }
                if (top1 == '*') { return '<'; }
                if (top1 == '/') { return '<'; }
            case '*':
                if (top1 == '+') { return '>'; }
                if (top1 == '-') { return '>'; }
                if (top1 == '*') { return '='; }
                if (top1 == '/') { return '='; }
            case '/':
                if (top1 == '+') { return '>'; }
                if (top1 == '-') { return '>'; }
                if (top1 == '*') { return '='; }
                if (top1 == '/') { return '='; }
            default:
                return '$';
        }
    }
}

class ArrayStack<T> {
    // top 表示栈顶，初始值是0
    private int top = 0;
    private T[] stackArray;
    private int size;

    public ArrayStack(int size) {
        this.size = size;
        stackArray = (T[])new Object[size];
    }

    public int size() {
        return top;
    }

    public T getTop() {
        if (size() == 0) {
            return null;
        }
        return (T)stackArray[top - 1];
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(T value) {
        if (!isFull()) {
            stackArray[top++] = value;
            System.out.printf("%s is push in\n", value);
        }
    }

    /**
     * 出栈
     */
    public T pop() {
        if (!isEmpty()) {
            T value = (T)stackArray[top - 1];
            System.out.printf("the index %d value %s is pop out!\n", top - 1, value);
            stackArray[top - 1] = null;
            top--;
            return value;
        }
        return null;
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
                    System.out.printf("the %s index value is %s \n", i, stackArray[i]);
                }
            }
        }
    }
}
