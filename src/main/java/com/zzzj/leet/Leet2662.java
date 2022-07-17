package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-09 21:57
 */
public class Leet2662 {

    public static int[] subSort(int[] array) {

        if (array.length < 2) {
            return new int[]{-1, -1};
        }

        int max = array[0];

        int N = array.length;

        int right = -1;

        for (int i = 1; i < N; i++) {
            if (array[i] < max) {
                right = i;
            } else {
                max = array[i];
            }
        }

        if (right == -1) {
            return new int[]{-1, -1};
        }

        int min = array[N - 1];

        int left = -1;

        for (int i = N - 2; i >= 0; i--) {
            if (array[i] > min) {
                left = i;
            } else {
                min = array[i];
            }
        }

        return new int[]{left, right};
    }

}
