package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-07 14:26
 */
public class Leet1846 {

    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {

        Arrays.sort(arr);

        int N = arr.length;

        arr[0] = 1;

        for (int i = 1; i < N; i++) {
            int sub = arr[i] - arr[i - 1];
            if (sub > 1) {
                arr[i] -= sub - 1;
            }
        }

        return arr[N - 1];
    }

}
