package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-27 15:04
 */
public class Leet96 {

    public static void main(String[] args) {
        System.out.println(numTrees(10));
    }

    // 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数
    public static int numTrees(int n) {
        if (n < 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            int result = 0;
            for (int j = 0; j < i; j++) {
                result += dp[j] * dp[i - j - 1];
            }
            dp[i] = result;
        }

        return dp[n];
    }

    public static int dfs(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 0) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        // 左边可能 * 右边可能
        int result = 0;

        for (int i = 0; i < n; i++) {
            result += dfs(i) * dfs(n - i - 1);
        }

        return result;
    }

}
