package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-08 15:11
 */
public class Leet120 {

    public static void main(String[] args) {
        System.out.println(minimumTotal(LeetUtils.convertLists("[[2],[3,4],[6,5,7],[4,1,8,3]]")));
        System.out.println(minimumTotal(LeetUtils.convertLists("[[-10]]")));
        System.out.println(minimumTotal(LeetUtils.convertLists("[[-1],[-2,-3]]")));
        System.out.println(minimumTotal(LeetUtils.convertLists("[[-1],[2,3],[1,-1,-3]]")));
    }

    // [[-1],[2,3],[1,-1,-3]]
    public static int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();

        int[] dp = new int[N];
        int[] copy = new int[N];

        dp[0] = triangle.get(0).get(0);
        copy[0] = dp[0];

        for (int i = 1; i < N; i++) {
            List<Integer> list = triangle.get(i);

            int size = list.size();

            dp[0] = copy[0] + list.get(0);

            for (int j = 1; j < size - 1; j++) {
                dp[j] = Math.min(copy[j], copy[j - 1]) + list.get(j);
            }

            dp[size - 1] = copy[size - 2] + list.get(size - 1);

            for (int j = 0; j < N; j++) {
                copy[j] = dp[j];
            }
        }

        return Arrays.stream(dp).min().orElse(0);
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int N = triangle.size();

        int[][] dp = new int[N][N];

        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < N; i++) {
            List<Integer> list = triangle.get(i);

            int size = list.size();

            dp[i][0] = dp[i - 1][0] + list.get(0);

            for (int j = 1; j < size - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + list.get(j);
            }

            dp[i][size - 1] = dp[i - 1][size - 2] + list.get(size - 1);
        }


        int ans = dp[N - 1][0];

        for (int i = 1; i < N; i++) {
            ans = Math.min(ans, dp[N - 1][i]);
        }

        return ans;
    }


}
