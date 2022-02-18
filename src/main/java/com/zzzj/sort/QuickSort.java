package com.zzzj.sort;

import com.zzzj.util.ArrayUtil;

import java.util.Random;

/**
 * @author Zzzj
 * @create 2021-04-12 22:24
 */
public class QuickSort {

    public static Random random = new Random();

    public static void sort(int[] arr) {
        partition(arr, 0, arr.length - 1);
    }

    public static void partition(int[] arr, int left, int right) {
        // 递归终止
        int originLeft = left;
        int originRight = right;

        // 优化整个数组是有序的情况下,算法退化成O(n²)的复杂度
        int idx = left + (random.nextInt(right) % (right - left + 1));
        ArrayUtil.swap(arr, idx, originLeft);

        int k = arr[originLeft];

        int j = right;
        int i = originLeft + 1;


        while (i < j) {
            if (arr[j] > k) {
                j--;
            } else if (arr[i] < k) {
                i++;
            } else {
                // arr[j] == k || arr[i] == k || arr[j] < k && arr[i] > [k]
                ArrayUtil.swap(arr, i, j);
                i++;
                j--;
            }
        }

        if (arr[j] < k) {
            ArrayUtil.swap(arr, originLeft, j);
        } else {
            j -= 1;
            ArrayUtil.swap(arr, originLeft, j);
        }

        if (j - 1 > originLeft) {
            partition(arr, originLeft, j - 1);
        }

        if (right > j + 1) {
            partition(arr, j + 1, right);
        }

    }

    public static void main(String[] args) {
        int[] array1 = ArrayUtil.generateArray(1000000);

        int[] array2 = ArrayUtil.generateNearlyOrderedArray(1000000, 100);

        int[] array3 = ArrayUtil.generateArray(1000000, 0, 10);

        // 137ms
        ArrayUtil.testSort(array1, QuickSort::sort);

        // stackoverflow -> 50ms
        ArrayUtil.testSort(array2, QuickSort::sort);

        // 974ms ->(*10) 45ms
        ArrayUtil.testSort(array3, QuickSort::sort);
    }


}
