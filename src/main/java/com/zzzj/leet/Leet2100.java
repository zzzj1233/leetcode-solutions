package com.zzzj.leet;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-06-06 18:22
 */
public class Leet2100 {

    public static void main(String[] args) {
        System.out.println(goodDaysToRobBank(new int[]{5, 3, 3, 3, 5, 6, 2}, 2));
    }

    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int N = security.length;

        int[] dp = new int[N];
        int[] dp2 = new int[N];

        // dp[i] = 上一个比我小的数的index

        dp[0] = -1;
        int preIndex = 0;

        for (int i = 1; i < N; i++) {
            if (security[i] <= security[preIndex]) {
                dp[i] = -1;
                preIndex = i;
            } else {
                dp[i] = preIndex;
            }
        }

        dp2[N - 1] = -1;

        preIndex = N - 1;

        for (int i = N - 2; i >= 0; i--) {

            if (security[i] <= security[i + 1]) {
                dp2[i] = preIndex;
            } else {
                preIndex = i;
                dp2[i] = -1;
            }

        }


        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(dp2));

        return null;
    }

    public static void dfs(int[] security, int time, int i) {
        if (i - time < 0) {
            return;
        }
        if (i + time >= security.length) {
            return;
        }

        // time ~ i 全部递减
        // i + 1 ~ i + time 递增
        // 那么是一个合适的答案

    }

}
