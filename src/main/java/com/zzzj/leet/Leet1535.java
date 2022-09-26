package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-16 11:17
 */
public class Leet1535 {

    public static int getWinner(int[] arr, int k) {

        int N = arr.length;

        int max = Math.max(arr[0], arr[1]);

        if (k == 1) {
            return max;
        }

        int count = 0;

        for (int i = 2; i < N; i++) {
            if (arr[i] < max) {
                count++;
            } else {
                max = arr[i];
                count = 1;
            }
            if (count == k) {
                return max;
            }
        }

        return max;
    }

}
