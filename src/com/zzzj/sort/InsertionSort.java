package com.zzzj.sort;

import com.zzzj.util.ArrayUtil;

/**
 * @author Zzzj
 * @create 2021-04-11 17:04
 */
public class InsertionSort {

    // 外层循环遍历数组
    // 内存循环把当前元素放到合适的位置(这个位置前所有的元素都比该元素小)

    public static void sort(int[] arr) {

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    ArrayUtil.swap(arr, i, j);
                }
            }
        }

    }


    public static void sort2(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    ArrayUtil.swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }

    }

    // 优化版,减少交换的次数
    public static void sort3(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] < arr[i - 1]) {
                int swapValue = arr[i];
                arr[i] = arr[i - 1];

                for (int j = i - 1; j >= 0; j--) {
                    if (j == 0 || arr[j - 1] < swapValue) {
                        arr[j] = swapValue;
                        // break提前终止会带来很大的性能提升
                        // 如果数组本身就是接近有序的,那么插入排序性能非常高
                        break;
                    } else {
                        arr[j] = arr[j - 1];
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] array1 = ArrayUtil.generateArray(100000);
        int[] array2 = ArrayUtil.generateNearlyOrderedArray(1000000, 100);
        // 9439ms
        // ArrayUtil.testSort(array, InsertionSort::sort);

        // 1377ms
        // ArrayUtil.testSort(array, InsertionSort::sort2);

        // 839ms
        ArrayUtil.testSort(array1, InsertionSort::sort3);

        // 数据量 * 10 , 但是花的时间任然极少
        // 38ms
        ArrayUtil.testSort(array2, InsertionSort::sort3);
    }

}
