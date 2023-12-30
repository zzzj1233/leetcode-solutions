package com.zzzj.dp;

import java.util.Arrays;

public class Lint588 {

    public static boolean canPartition(int[] nums) {

        int s = Arrays.stream(nums).sum();

        if (s % 2 != 0)
            return false;

        int h = s / 2;

        boolean[] f = new boolean[h + 1];

        f[0] = true;

        for (int w : nums) {

            for (int v = h; v >= w; v--) {
                f[v] = f[v] || f[v - w];
            }

        }

        return f[h];
    }

}
