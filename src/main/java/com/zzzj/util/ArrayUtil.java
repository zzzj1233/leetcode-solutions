package com.zzzj.util;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author Zzzj
 * @create 2021-04-11 16:24
 */
public class ArrayUtil {

    public static int[][] copy(int[][] arr) {
        int[][] result = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            result[i] = copy(arr[i]);
        }
        return result;
    }

    public static int[] copy(int[] arr) {
        return Arrays.copyOfRange(arr, 0, arr.length);
    }

    public static int[] copy(int[] arr, int i, int j) {
        int[] ret = new int[j - i + 1];

        for (int k = 0; k < ret.length; k++) {
            ret[k] = arr[i + k];
        }

        return ret;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean sorted(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static void testSort(int[] arr, Consumer<int[]> consumer) {
        long start = System.currentTimeMillis();
        consumer.accept(arr);
        long end = System.currentTimeMillis();
        if (!sorted(arr)) {
            throw new RuntimeException("Not sorted");
        }
        System.out.println("耗时 : " + (end - start) + " ms");
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " , ");
        }

        System.out.println();
    }

    public static int[] generateArray(int n) {
        return generateArray(n, 0, 10000);
    }

    public static int[] generateNearlyOrderedArray(int n, int swapTime) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        Random random = new Random();

        for (int i = 0; i < swapTime; i++) {
            swap(arr, random.nextInt(n), random.nextInt(n));
        }

        return arr;
    }

    public static int[] generateArray(int n, int rangL, int rangR) {
        int[] arr = new int[n];

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(rangR) + rangL;
        }

        return arr;
    }

    public static int[] stepUpArray(int n, int rangL, int randStep) {
        int[] arr = new int[n];

        int min = rangL;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = LeetUtils.random.nextInt(randStep) + 1 + min;
            min = arr[i];
        }

        return arr;
    }

    public static int[] distinct(int[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .toArray();
    }

    public static int[][] copy(int[] arr, int count) {
        int[][] ret = new int[count][];

        for (int i = 0; i < count; i++) {
            ret[i] = copy(arr);
        }

        return ret;
    }

    public static int[] distinctArray(int N){

        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = i + 1;

        cn.hutool.core.util.ArrayUtil.shuffle(arr);

        return arr;
    }
    public static int[] sort(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

}
