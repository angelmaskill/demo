package com.algorithm.sort;

import java.util.Arrays;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @Author: AngelMa
 * @Description: 冒泡排序
 * @Date: Created on 2021/4/14 9:40 下午
 * @Modified By:
 */
public class BubbleSort {
    public static void main(String[] args) {
        // 初始化数组
        int[] array = initArray();
        // 进行排序
        long begin = System.currentTimeMillis();
        sortBubbleBigRight(array);
        long end = System.currentTimeMillis();
        System.out.println("最终时间消耗：" + (end - begin) / 1000 + " s");

    }

    private static int[] initArray() {
        //int[] array = {30, 9, -1, -10, -20};
        //int[] array = {3, 9, -1, 10, 20};
        //int[] array = {1, 2, 3, 4, 5, 6};
        int size = 8;
        //int size = 8;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RandomUtils.nextInt(size + 1);
        }
        return array;
    }

    /**
     * 大泡泡冒到右边
     *
     * @param array
     */
    private static void sortBubbleBigRight(int[] array) {
        int left = 0;
        int right = 0;
        for (int j = 0; j < array.length - 1; j++) {
            System.out.println("\n第" + (j + 1) + "趟排序开始");
            boolean isBubbleUp = false;
            /*
            优化 1：每趟的比对次数是递减的。
            第1趟排序，需要对比 length-1 次
            第2趟排序，需要对比 length-2 次
            */
            for (int i = 0; i < array.length - j; i++) {
                if (i + 1 <= array.length - 1) {
                    left = array[i];
                    right = array[i + 1];
                    if (right < left) {
                        array[i] = right;
                        array[i + 1] = left;
                        isBubbleUp = true;
                        printArray("发生位置互换", array);
                    }
                }
            }
            /**
             * 优化 2：如果某趟比对没有发生数据交换，则说明数组已经完全有序，可以提前退出。
             */
            if (!isBubbleUp) {
                System.out.println("第" + (j + 1) + "趟未发生 1 次排序，提前结束排序");
                break;
            }
            printArray("第" + (j + 1) + "趟排序结束", array);
        }
    }

    private static void printArray(String desc, int[] array) {
        System.out.println(desc + Arrays.toString(array));
    }
}
