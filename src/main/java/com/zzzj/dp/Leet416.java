package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-10-29 14:22
 */
public class Leet416 {


    public static void main(String[] args) {

        System.out.println(canPartition(new int[]{100}));

    }

    public static boolean canPartition(int[] nums) {

        int N = nums.length;

        int s = Arrays.stream(nums).sum();

        if (s % 2 != 0)
            return false;

        int h = s / 2;

        boolean[] f = new boolean[h + 1];

        f[0] = true;

        for (int i = 0; i < N; i++) {

            for (int v = h; v >= nums[i]; v--) {
                f[v] |= f[v - nums[i]];
            }

        }

        return f[h];
    }

}
