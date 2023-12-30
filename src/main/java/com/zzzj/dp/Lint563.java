package com.zzzj.dp;

public class Lint563 {

    public static int backPackV(int[] nums, int target) {

        int N = nums.length;

        int[] f = new int[target + 1];

        f[0] = 1;

        for (int w : nums) {

            for (int v = target; v >= w; v--) {
                f[v] += f[v - w];
            }

        }

        return f[target];
    }

}
