package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-20 17:02
 */
public class Leet651 {

    // 1327104
    public static void main(String[] args) {
        System.out.println(maxA(3));
        System.out.println(maxA(20));

    }

    public static int maxA(int n) {
        return dp(n);
    }

    public static int dp(int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // 多打一个A字符
            dp[i] = dp[i - 1] + 1;
            // 或者从 i - 2处开始复制,可以复制 0 ~ N次
            // 全选加复制 = 两次
            int start = i - 2;

            for (int j = i - 3; j >= 1; j--) {
                dp[i] = Math.max(dp[i], dp[j] * (start - j + 1));
            }
        }

        return dp[n];
    }

}
