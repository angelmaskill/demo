package com.algorithm.sort;

import java.util.Arrays;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @Author: AngelMa
 * @Description:选择排序
 * @Date: Created on 2021/4/16 6:50 上午
 * @Modified By:
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] init = initBigArray();
        // 进行排序
        long begin = System.currentTimeMillis();
        selectsort(init);
        long end = System.currentTimeMillis();
        System.out.println("最终时间消耗：" + (end - begin) / 1000 + " s");
    }

    private static int[] init() {
        int[] array = {101, 34, 119, 1, -1, 90, 123};
        return array;
    }


    private static int[] initBigArray() {
        //int[] array = {30, 9, -1, -10, -20};
        //int[] array = {3, 9, -1, 10, 20};
        //int[] array = {1, 2, 3, 4, 5, 6};
        int size = 80000;
        //int size = 8;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RandomUtils.nextInt(size + 1);
        }
        return array;
    }
    /**
     * 遍历找出最小值
     *
     * @param array
     */
    private static void selectsort(int array[]) {
        int init_value = -999;
        for (int i = 1; i <= array.length - 1; i++) {
            int min_value = init_value;
            int min_index = 0;
            int temp = 0;
            for (int j = i - 1; j < array.length; j++) {
                if (min_value == init_value) {
                    min_value = array[j];
                } else if (min_value > array[j]) {
                    min_value = array[j];
                    min_index = j;
                }
            }
            //System.out.println("第" + i + "轮的最小值 min: " + min_value);
            if (array[i - 1] != min_value) {
                temp = array[i - 1];
                array[i - 1] = min_value;
                array[min_index] = temp;
            }
            //System.out.println("第" + i + "轮最终的数组：" + Arrays.toString(array));
        }
    }

}
