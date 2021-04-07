import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Author: AngelMa
 * @Description: 中缀表达式转后缀表达式
 * @Date: Created on 2021/4/7 10:27 下午
 * @Modified By:
 */
public class Infix2PostFix {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4+1)-10*8/2-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.print(strings + "\n");
        List<String> list = toSuffixExpression(strings);
        System.out.print(calculate(list));
    }

    public static int calculate(List<String> ls) {
        // 创建给栈, 只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        // 遍历 ls
        for (String item : ls) {
            // 这里使用正则表达式来取出数
            if (item.matches("\\d+")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算， 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res 入栈
                stack.push("" + res);
            }

        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

    /*具体步骤如下:
    1.初始化两个栈：运算符栈s1和储存中间结果的栈s2；
    2.从左至右扫描中缀表达式；
    3.遇到操作数时，将其压s2；
    4.遇到运算符时，比较其与s1栈顶运算符的优先级：
        1）如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
        2）否则，若优先级比栈顶运算符的高，也将运算符压入s1；
        3）否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
    5.遇到括号时：
        (1) 如果是左括号“(”，则直接压入s1
        (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
    6.重复步骤2至5，直到表达式的最右边
    7.将s1中剩余的运算符依次弹出并压入s2
    8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式*/
    private static List<String> toSuffixExpression(List<String> list) {
        Stack s1 = new Stack();
        Stack s2 = new Stack();
        int cnt = 0;
        for (String s : list) {
            // 遇到操作数时，将其压s2；
            if (s.matches("\\d+")) {
                s2.push(s);
            }
            /*遇到运算符时，比较其与s1栈顶运算符的优先级：
            1）如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
            2）否则，若优先级比栈顶运算符的高，也将运算符压入s1；
            3）否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；*/
            char ch = s.charAt(0);
            if (isOperator(ch)) {//遇到运算符
                do {
                    if (s1.isEmpty()) {
                        s1.push(ch);
                        break;
                    } else if ('(' == (Character)s1.peek() || '>' == isPriorityHigh(ch,
                        (Character)s1.peek())) {
                        s1.push(ch);
                        break;
                    } else if ('=' == isPriorityHigh(ch, (Character)s1.peek())) {
                        s2.push(s1.pop());
                        s1.push(ch);
                        break;
                    } else {
                        s2.push(s1.pop());
                    }
                } while (!s1.isEmpty());
            }
            /*遇到括号时：
            (1) 如果是左括号“(”，则直接压入s1
            (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃*/
            if (isBracket(ch)) {
                if ('(' == ch) {
                    s1.push(ch);
                } else if (')' == ch) {
                    char pop;
                    while ('(' != (pop = (Character)s1.pop())) {
                        s2.push(pop);
                    }
                }
            }
            cnt++;
            System.out.println("s1:" + cnt + s1.toString());
            System.out.println("s2:" + cnt + s2.toString());
        }
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        List<String> result = new ArrayList();
        while (!s2.isEmpty()) {
            result.add(String.valueOf(s2.pop()));
        }
        Collections.reverse(result);
        return result;
    }

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.

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

    private static boolean isBracket(char charAt) {
        if (charAt == '(' || charAt == ')') {
            return true;
        }
        return false;
    }

    /**
     * 将中缀表达式转换为表达式列表
     *
     * @param expression
     * @return
     */
    private static List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int i = 0; //遍历的指针
        do {
            char c = expression.charAt(i);
            if (!Character.isDigit(c)) {
                list.add("" + c);
                i++;
            } else {//如果是一个数，需要考虑多位数的情况
                String s = "";//用于多位数的拼接
                //如果是数字，就一直拼接
                while (i < expression.length() && Character.isDigit(c = expression.charAt(i))) {
                    s += c;
                    i++;
                }
                list.add("" + s);

            }
        } while (i < expression.length());
        return list;
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


