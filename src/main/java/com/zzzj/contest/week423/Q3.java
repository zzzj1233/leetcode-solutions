package com.zzzj.contest.week423;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-11-10 10:54
 */
public class Q3 {

    public static void main(String[] args) {

        System.out.println(sumOfGoodSubsequences(new int[]{1, 2, 1}));

    }

    public static int sumOfGoodSubsequences(int[] nums) {

        int N = nums.length;

        final int MOD = 1000000007;

        long ans = 0;

        int max = Arrays.stream(nums).max().orElse(0);

        long[][] f = new long[max + 2][2];

        for (int num : nums) {

            long sum = num - 1 >= 0 ? ((f[num - 1][0] * num) % MOD) + (f[num - 1][1]) : 0;

            sum = (sum + ((f[num + 1][0] * num) % MOD) + (f[num + 1][1])) % MOD;

            f[num][0] = (f[num][0] + (f[num + 1][0] + (num - 1 >= 0 ? f[num - 1][0] : 0) + 1)) % MOD;

            f[num][1] = (f[num][1] + sum + num) % MOD;

        }

        for (long[] item : f) {
            ans = (ans + item[1]) % MOD;
        }

        return (int) ans;
    }

}
