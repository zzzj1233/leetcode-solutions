package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-14 14:32
 */
public class Leet96 {


    public static void main(String[] args) {
        System.out.println(numTrees(4));
    }

    public static int numTrees(int n) {
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            ans += dfs(i, 1, n);
        }

        return ans;
    }

    public static int dfs(int i, int left, int right) {
        if (i <= 0) {
            return 1;
        }

        // 左边有多少种选择
        // int leftChoose = dfs();

        return -1;
    }


}
