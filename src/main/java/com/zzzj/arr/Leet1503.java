package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-07-27 16:05
 */
public class Leet1503 {


    public static int getLastMoment(int n, int[] left, int[] right) {
        int ans = 0;

        for (int i = 0; i < left.length; i++) {
            ans = Math.max(left[i], ans);
        }

        for (int i = 0; i < right.length; i++) {
            ans = Math.max(ans, n - right[i]);
        }

        return ans;
    }

}
