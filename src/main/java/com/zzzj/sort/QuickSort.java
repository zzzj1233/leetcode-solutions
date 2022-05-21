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
        // partitionTwoWays(arr, 0, arr.length - 1);
        partitionThreeWays(arr, 0, arr.length - 1);
    }

    public static void partitionTwoWays(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // <= | >
        int randomIdx = random.nextInt(right - left) + left;

        ArrayUtil.swap(arr, right, randomIdx);

        int num = arr[right];

        int l = left - 1;
        // 如果当时数小于= num,则和l+1位置的元素互换
        // l++,i++
        //

        int r = left;
        while (r <= right - 1) {
            if (arr[r] <= num) {
                ArrayUtil.swap(arr, l + 1, r);
                l++;
            }
            r++;
        }

        ArrayUtil.swap(arr, right, l + 1);

        partitionTwoWays(arr, left, l);

        partitionTwoWays(arr, l + 2, right);

    }

    public static void partitionThreeWays(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // < = >

        // left - l  <
        // l - i     =
        // i - right >
        int randomIdx = random.nextInt(right - left) + left;

        ArrayUtil.swap(arr, right, randomIdx);

        int num = arr[right];

        int l = left - 1;
        int i = left;
        int r = right - 1;

        while (i < r) {
            if (arr[i] < num) {
                ArrayUtil.swap(arr, i, ++l);
                i++;
            } else if (arr[i] == num) {
                i++;
            } else {
                ArrayUtil.swap(arr, i, r--);
            }
        }

        ArrayUtil.swap(arr, right, i + 1);

        partitionThreeWays(arr, left, i);

        partitionThreeWays(arr, i + 2, right);
    }

    public static void main(String[] args) {
        int[] array1 = ArrayUtil.generateArray(10, 0, 5);

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
