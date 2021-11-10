package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-11-04 14:18
 */
public class Leet338 {

    /**
     * 给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2
     * 输出：[0,1,1]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * <p>
     * 示例 2：
     * <p>
     * 输入：n = 5
     * 输出：[0,1,1,2,1,2]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 3 --> 11
     * 4 --> 100
     * 5 --> 101
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(5)));
    }

    public static int[] countBits(int n) {
        // 1 = 0001 = 1
        // 2 = 0010 = 1
        // 3 = 0011 = 2
        // 4 = 0100 = 1
        // 5 = 0000 0101 = 2
        // 6 = 0000 0110 = 2
        // 7 = 0000 0111 = 3
        // 8 = 0000 1000 = 1
        // 9 = 0000 1001 = 2
        // 10 = 0000 1010 = 2
        // 11 = 0000 1011 = 3
        // 12 = 0000 1100 = 2

        if (n == 0) {
            return new int[]{0};
        }

        if (n == 1) {
            return new int[]{0, 1};
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        return dp;
    }

}
