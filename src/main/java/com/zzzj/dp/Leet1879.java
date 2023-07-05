package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-03 16:46
 */
public class Leet1879 {

    public static void main(String[] args) {

        System.out.println(minimumXORSum(new int[]{1, 2}, new int[]{2, 3}));

        System.out.println(minimumXORSum(new int[]{1, 0, 3}, new int[]{5, 3, 4}));

        System.out.println(minimumXORSum(new int[]{9606269, 5221932, 7334481, 8439484, 5986425, 8864979, 5430580, 14172, 2078710, 7420803, 7542233}, new int[]{5875595, 5113681, 9047874, 6700866, 5693602, 9586753, 8259408, 1897425, 6334375, 6415366, 3421110}));

    }

    public static int minimumXORSum(int[] nums1, int[] nums2) {
        int N = nums1.length;

        int[] dp = new int[1 << N];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {

            int bc = Integer.bitCount(i);

            for (int j = 0; j < N; j++) {

                if ((i & (1 << j)) != 0) {

                    int xor = nums1[bc - 1] ^ nums2[j];

                    dp[i] = Math.min(dp[i], xor + dp[i ^ (1 << j)]);

                }

            }

        }

        return dp[dp.length - 1];
    }

}
