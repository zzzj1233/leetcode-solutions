package com.zzzj.dp;

public class Lint562 {

    public static int backPackIV(int[] nums, int target) {

        int N = nums.length;

        int[] f = new int[target + 1];

        f[0] = 1;

        for (int w : nums) {

            for (int v = w; v <= target; v++) {
                f[v] += f[v - w];
            }

        }

        return f[target];
    }

}
