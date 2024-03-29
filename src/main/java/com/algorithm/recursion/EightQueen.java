package com.algorithm.recursion;

/**
 * @Author: AngelMa
 * @Description: 8皇后问题解法
 * @Date: Created on 2021/4/10 12:53 下午
 * @Modified By:
 */
public class EightQueen {

    //定义一个max表示共有多少个皇后
    int max = 8;

    /**
     * 数组的下标= 放置的是第几个皇后 = 放置所在行
     * 数组的值  = 放置的列
     * <pre>
     *      定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
     *      对应 arr 下标 表示第几行，即第几个皇后，
     *      arr[i] = lombok.val , val 表示第 i+1 个皇后，放在第 i+1 行的第 val+1 列
     * </pre>
     */
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        //测试一把 ， 8皇后是否正确
        EightQueen queen8 = new EightQueen();
        queen8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w

    }

    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n) {
        if (n == max) {  //n = 8 , 其实8个皇后就既然放好
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            System.out.printf("尝试将皇后%d 放到第%d 列,", n, i);
            //先把当前这个皇后 n , 放到该行的第i列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) { // 不冲突
                //接着放n+1个皇后,即开始递归
                System.out.printf("继续放置%d个皇后.",n+1);
                check(n + 1); //
            }else{
                System.out.print("与原来位置产生冲突.");
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行得 后移的一个位置
        }
    }

    //查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 说明
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            // n = 1  放置第 2列 1 n = 1 array[1] = 1
            // Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        System.out.printf("第%d种解法：", count);
        for (int i = 0; i < array.length; i++) {
            System.out.printf( array[i] + " ");
        }
        System.out.println();
    }
}

