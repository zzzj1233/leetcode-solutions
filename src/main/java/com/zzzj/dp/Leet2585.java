package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-08-02 18:18
 */
public class Leet2585 {

    public static void main(String[] args) {

        System.out.println(waysToReachTarget(6, LeetUtils.convertInts("[[6,1],[3,2],[2,3]]")));

        System.out.println(waysToReachTarget(5, LeetUtils.convertInts("[[50,1],[50,2],[50,5]]")));

        System.out.println(waysToReachTarget(18, LeetUtils.convertInts("[[6,1],[3,2],[2,3]]")));

        System.out.println(waysToReachTarget(500, LeetUtils.convertInts("[[6,1],[49,2],[33,3],[26,4],[28,5],[45,6],[4,7],[23,8],[46,9],[39,10],[12,11],[28,12],[37,13],[18,14],[10,15],[27,16],[26,17],[10,18],[34,19],[11,20],[35,21],[5,22],[47,23],[19,24],[15,25],[27,26],[50,27],[3,28],[24,29],[18,30],[49,31],[32,32],[18,33],[5,34],[34,35]]")));

    }

    static final int MOD = 1000000007;

    public static int waysToReachTarget(int target, int[][] types) {

        int N = types.length;

        int[][] dp = new int[N + 1][target + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {

            int[] type = types[i - 1];

            int cnt = type[0];

            int score = type[1];

            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            for (int j = 1; j <= cnt; j++) {
                int s = j * score;

                for (int k = 1; k <= target; k++) {
                    if (k - s >= 0) {
                        dp[i][k] += dp[i - 1][k - s] % MOD;
                        dp[i][k] %= MOD;
                    }
                }

            }


        }

        return dp[N][target] % MOD;
    }

    public static int dfs(int target, int index, int[][] types) {

        if (target == 0) return 1;

        if (index >= types.length) return 0;

        int[] type = types[index];

        int cnt = type[0];

        int score = type[1];

        int result = dfs(target, index + 1, types);

        for (int i = 1; i <= cnt; i++) {

            int remain = target - score * i;

            if (remain < 0) break;

            result += dfs(remain, index + 1, types);
        }

        return result;
    }

}
