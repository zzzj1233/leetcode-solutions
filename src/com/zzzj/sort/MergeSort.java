package com.zzzj.sort;

import com.zzzj.util.ArrayUtil;

/**
 * @author Zzzj
 * @create 2021-04-11 19:23
 */
public class MergeSort {

    public static void sort(int[] arr) {
        merge(arr, 0, arr.length - 1);
    }

    // 自顶向下进行归并
    public static int[] merge(int[] arr, int i, int j) {
        if (j - i > 1) {
            int sub = (i + j) / 2;
            int[] left = merge(arr, i, sub);

            // sub + 1 为偶数时小于j
            // 为奇数时 == j

            int[] right = merge(arr, sub + 1, j);
            // 递归回来后 , i ~ sub 和 sub + 1 ~ j 一定是有序的
            // 合并

            int leftIndex = 0;
            int rightIndex = 0;

            if (left[left.length - 1] >= right[0]) {
                for (int k = 0; k <= j - i; k++) {
                    if (rightIndex >= right.length) {
                        arr[i + k] = left[leftIndex];
                        leftIndex++;
                    } else if (leftIndex >= left.length) {
                        arr[i + k] = right[rightIndex];
                        rightIndex++;
                    } else if (left[leftIndex] <= right[rightIndex]) {
                        arr[i + k] = left[leftIndex];
                        leftIndex++;
                    } else {
                        arr[i + k] = right[rightIndex];
                        rightIndex++;
                    }
                }
            }

            return ArrayUtil.copy(arr, i, j);
        } else {
            // j - i == 1
            if (arr[j] < arr[i]) {
                ArrayUtil.swap(arr, i, j);
            }
            return ArrayUtil.copy(arr, i, j);
        }
    }




    public static void main(String[] args) {
        int[] array1 = ArrayUtil.generateArray(1000000);
        int[] array2 = ArrayUtil.generateNearlyOrderedArray(1000000, 100);

        // 158
        ArrayUtil.testSort(array1, MergeSort::sort);

        // 一个完全有序的数组
        // 48ms
        ArrayUtil.testSort(array2, MergeSort::sort);
    }


}
