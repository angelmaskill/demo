package com.algorithm.stack;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

/**
 * @Author: AngelMa
 * @Description: 逆波兰表达式的计算
 * @Date: Created on 2021/4/5 11:25 上午
 * @Modified By:
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        Stack dataStack = new Stack();
        Stack operStack = new Stack();
        //String expression = "30 4 + 5 * 6 -";
        String expression = "4 5 * 8 - 60 + 8 2 / +";
        String keepNum = ""; //用于拼接 多位数
        String[] s = expression.split(" ");
        for (String s1 : s) {
            if (StringUtils.isBlank(s1)) {
                continue;
            }
            if (s1.matches("\\d+")) {
                // 如果是数字，直接入数栈
                dataStack.push(s1);
            } else {
                // 解析操作符，入操作符栈
                char aChar = s1.charAt(0);
                operStack.push(aChar);
                pushOperator(dataStack, operStack, aChar);
            }
        }
        //获取最终的结果
        getFinalResult(dataStack, operStack);
    }

    private static void pushOperator(Stack dataArrayStack, Stack operArrayStack, char aChar) {
        parseAndPushData(dataArrayStack, operArrayStack, aChar);
    }

    private static void getFinalResult(Stack dataArrayStack, Stack operArrayStack) {
        while (dataArrayStack.size() > 1) {
            int num1 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
            int num2 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
            char operator = (char)operArrayStack.pop();
            Object top = operArrayStack.peek();
            int calResult = Calculator.cal(num1, num2, operator);
            dataArrayStack.push(calResult);
        }
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
        int finalResult = (int)dataArrayStack.pop();
        System.out.println(finalResult);
    }

    private static void parseAndPushData(Stack dataArrayStack, Stack operArrayStack, char aChar) {
        int num1 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
        int num2 = Integer.valueOf(String.valueOf(dataArrayStack.pop()));
        char operator = (char)operArrayStack.pop();
        int calResult = Calculator.cal(num1, num2, operator);
        dataArrayStack.push(calResult);
    }
}

class Calculator1 {
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

}
