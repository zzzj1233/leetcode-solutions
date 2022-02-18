package com.zzzj.sort;

import com.zzzj.util.ArrayUtil;

/**
 * @author Zzzj
 * @create 2021-04-11 16:24
 */
public class SelectionSort {

    // 外层循环遍历数组
    // 内层循环找到最小的元素,然后放到数组的最前面

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;

            // 找最小的元素
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // 交换
            ArrayUtil.swap(arr, i, minIdx);
        }
    }

    public static void main(String[] args) {
        int[] array = ArrayUtil.generateArray(100000);
        ArrayUtil.testSort(array, SelectionSort::sort);
    }

}
