package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-10-11 17:37
 */
public class Leet1955 {

    public static void main(String[] args) {

        System.out.println(countSpecialSubsequences(new int[]{0, 1, 2, 2}));

        System.out.println(countSpecialSubsequences(new int[]{0, 1, 2, 0, 1, 2}));

    }

    public static int countSpecialSubsequences(int[] nums) {

        int N = nums.length;

        long[] f = new long[4];

        f[0] = 1;

        final int MOD = 1000000007;

        for (int i = 0; i < N; i++) {
            f[nums[i] + 1] = ((f[nums[i] + 1] << 1) + f[nums[i]]) % MOD;
        }

        return (int) f[3];
    }

}
