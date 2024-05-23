package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-04-02 10:54
 */
public class Leet3098 {

    public static void main(String[] args) {

        System.out.println(sumOfPowers(new int[]{1, 2, 3, 4}, 3));

//        System.out.println(sumOfPowers(new int[]{4, 3, -1}, 2));

    }

    public static int sumOfPowers(int[] nums, int k) {

        int N = nums.length;

        Arrays.sort(nums);

        // 0 : len
        // 1 : index
        // 2 : { 0 = min : 1 = cnt }
        long[][][] f = new long[N][N][2];

        final int MOD = (int) (1e9 + 7);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                f[i][j][0] = Long.MAX_VALUE;

        long ans = 0;

        for (int len = 1; len < k; len++) {

            for (int index = len; index < N; index++) {

                for (int pe = len - 1; pe < index; pe++) {


                }

            }

        }

        for (int index = 0; index < N; index++) {
            ans = (ans + (f[k - 1][index][0] * f[k - 1][index][1]) % MOD) % MOD;
        }

        return (int) ans;
    }

}
