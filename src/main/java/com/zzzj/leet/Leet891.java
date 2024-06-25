package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-06-25 16:59
 */
public class Leet891 {

    public static void main(String[] args) {
        System.out.println(sumSubseqWidths(new int[]{1, 2, 3}));
    }

    static final int MOD = 1000000007;

    public static int sumSubseqWidths(int[] nums) {

        int N = nums.length;

        long[] pw = new long[N];

        for (int i = 0; i < N; i++) {
            pw[i] = pow(2, i);
        }

        Arrays.sort(nums);

        long ans = 0;

        for (int i = 0; i < N; i++) {
            ans = (ans + nums[i] * pw[i] - nums[i] * pw[N - i - 1]) % MOD;
        }

        return (int) ans;
    }

    private static long pow(long x, int n) {
        long res = 1L;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) res = res * x % MOD;
            x = x * x % MOD;
        }
        return res;
    }

}
