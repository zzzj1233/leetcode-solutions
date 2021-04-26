package com.zzzj.sort;

import com.zzzj.util.ArrayUtil;

/**
 * @author Zzzj
 * @create 2021-04-11 21:52
 */
public class MergeSortUT {

    // 自底向上进行归并
    public static void sort(int[] arr) {
        // 外层循环是要做x次归并
        for (int i = 1; i < arr.length; i <<= 1) {

            for (int j = 0; j < arr.length && j + i - 1 < arr.length; j += i << 1) {
                // 内层循环对一个小区间做 -> 合并
                int[] left = ArrayUtil.copy(arr, j, j + i - 1);

                int rightR = j + (i << 1) - 1;

                if (rightR >= arr.length) {
                    rightR = arr.length - 1;
                }

                int[] right = ArrayUtil.copy(arr, j + i, rightR);

                // 合并
                int leftIdx = 0;
                int rightIdx = 0;

                if (left.length == 0 || right.length == 0 || left[Math.max(left.length - 1, 0)] <= right[0]) {
                    continue;
                }

                for (int k = j; k <= rightR; k++) {
                    if (leftIdx >= left.length) {
                        arr[k] = right[rightIdx];
                        rightIdx++;
                    } else if (rightIdx >= right.length) {
                        arr[k] = left[leftIdx];
                        leftIdx++;
                    } else if (left[leftIdx] <= right[rightIdx]) {
                        arr[k] = left[leftIdx];
                        leftIdx++;
                    } else {
                        arr[k] = right[rightIdx];
                        rightIdx++;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] array = ArrayUtil.generateArray(1000000);
        int[] array2 = ArrayUtil.generateNearlyOrderedArray(1000000, 100);

        // 158ms
        ArrayUtil.testSort(array, MergeSortUT::sort);

        // 72ms
        ArrayUtil.testSort(array2, MergeSortUT::sort);
    }

}
