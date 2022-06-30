package com.zzzj.leet;


import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-28 14:45
 */
public class Leet2654 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(subSort(new int[]{0, 1, 2, 3, 5, 4, 6, 9, 8, 7})));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateNearlyOrderedArray(10, 2);

            if (!Arrays.equals(subSort(arr), right(arr))) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }

        System.out.println("Ok");
    }

    // 1,2,4,[7,10,11,7,12,6,7],16,18,19
    // 3 9
    public static int[] subSort(int[] array) {

        if (array.length < 2) {
            return new int[]{-1, -1};
        }


        int N = array.length;

        int[] max = new int[N];
        int[] min = new int[N];

        max[0] = array[0];
        min[N - 1] = array[N - 1];

        for (int i = 1; i < array.length; i++) {
            max[i] = Math.max(max[i - 1], array[i]);
        }

        for (int i = N - 2; i >= 0; i--) {
            min[i] = Math.min(min[i + 1], array[i]);
        }

        int left = -1;
        int right = -1;

        for (int i = 0; i < array.length; i++) {
            if (i < array.length && ((i + 1 < min.length && array[i] > min[i + 1]) || (i - 1 >= 0 && array[i] < max[i - 1]))) {
                if (left == -1) {
                    left = i;
                }
                while (i < array.length && ((i + 1 < min.length && array[i] > min[i + 1]) || (i - 1 >= 0 && array[i] < max[i - 1]))) {
                    i++;
                }
                right = i - 1;
            }
        }

        return new int[]{left, right};
    }


    public static int[] right(int[] array) {
        int N = array.length, start = -1, end = -1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        // 从前往后找目标末位，使得从该位到最后，数组保持递增
        for (int i = 0; i < N; i++) {
            if (array[i] >= max) max = array[i];
            else end = i;
        }

        // 数组恒递增，说明数组是有序的，直接返回
        if (end == -1) return new int[]{-1, -1};

        // 从后往前找目标首位，使得从该位到最前，数组保持递减
        for (int i = end; i >= 0; i--) {
            if (array[i] <= min) min = array[i];
            else start = i;
        }
        return new int[]{start, end};
    }


}