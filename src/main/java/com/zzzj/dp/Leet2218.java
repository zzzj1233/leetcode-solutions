package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-26 17:18
 */
public class Leet2218 {

    public static void main(String[] args) {
        System.out.println(maxValueOfCoins(LeetUtils.convertLists("[[1,100,3],[7,8,9]]"), 2));
        System.out.println(maxValueOfCoins(LeetUtils.convertLists("[[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]]"), 7));
    }

    public static int maxValueOfCoins(List<List<Integer>> piles, int k) {
        return dp(piles, k);
    }

    public static int dp(List<List<Integer>> piles, int k) {
        int N = piles.size();

        int[][] dp = new int[N][k + 1];


        int sum = 0;

        for (int i = 1; i <= k; i++) {
            if (i <= piles.get(0).size()) {
                sum += piles.get(0).get(i - 1);
            }
            dp[0][i] = sum;
        }

        for (int i = 1; i < N; i++) {

            List<Integer> list = piles.get(i);

            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];
                sum = 0;

                int end = Math.min(list.size() + 1, j);
                for (int h = 1; h <= end; h++) {
                    if (h <= list.size()) {
                        sum += list.get(h - 1);
                    }
                    dp[i][j] = Math.max(dp[i][j], sum + dp[i - 1][j - h]);
                }
            }

        }

        return dp[N - 1][k];
    }

    public static int dfs(List<List<Integer>> piles, int k, int[] indexes) {
        if (k == 0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < piles.size(); i++) {
            List<Integer> list = piles.get(i);
            if (indexes[i] >= list.size()) {
                continue;
            }
            indexes[i]++;
            result = Math.max(result, list.get(indexes[i] - 1) + dfs(piles, k - 1, indexes));
            indexes[i]--;
        }

        return result;
    }

}
