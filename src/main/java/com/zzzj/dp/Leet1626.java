package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-16 16:59
 */
public class Leet1626 {


    public static int bestTeamScore(int[] scores, int[] ages) {

        int N = scores.length;

        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            arr[i][0] = ages[i];
            arr[i][1] = scores[i];
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int[] dp = new int[N];

        int ans = 0;

        // 年龄比你小,分数比你高,就会存在矛盾
        for (int i = 0; i < N; i++) {
            int age = arr[i][0];
            int score = arr[i][1];

            dp[i] = score;

            // age <= i
            for (int j = 0; j < i; j++) {

                int ageJ = arr[j][0];
                int scoreJ = arr[j][1];

                if (ageJ == age || scoreJ <= score) {
                    dp[i] = Math.max(dp[i], dp[j] + score);
                }

            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


}
