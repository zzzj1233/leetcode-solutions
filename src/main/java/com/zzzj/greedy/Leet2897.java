package com.zzzj.greedy;

import java.util.List;

/**
 * @author zzzj
 * @create 2023-11-07 16:42
 */
public class Leet2897 {

    public static int maxSum(List<Integer> nums, int k) {

        final int MOD = 1000000007;

        int[] tab = new int[32];

        for (Integer num : nums)
            for (int i = 0; i < 32; i++)
                if ((num & (1 << i)) != 0)
                    tab[i]++;

        long ans = 0;

        for (int i = 0; i < k; i++) {

            long value = 0;

            for (int x = 31; x >= 0; x--) {
                if (tab[x] > 0) {
                    value |= 1 << x;
                    tab[x]--;
                }
            }

            ans = (ans + (value * value)) % MOD;
        }

        return (int) ans;
    }

}
