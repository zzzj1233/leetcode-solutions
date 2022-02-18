package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-27 15:04
 */
public class Leet96 {

    // 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数
    public static int numTrees(int n) {
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;

        int root = 2;
        int leaf = 2;



        return memo[n];
    }

}
