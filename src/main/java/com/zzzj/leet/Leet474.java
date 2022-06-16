package com.zzzj.leet;


/**
 * @author zzzj
 * @create 2022-05-20 20:26
 */
public class Leet474 {

    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] counts = zeroCount(strs);
        return dp(counts, m, n);
    }

    public static int dp(int[][] counts, int M, int N) {
        int len = counts.length;

        int[][][] dp = new int[len + 1][M + 1][N + 1];

        for (int i = len - 1; i >= 0; i--) {

            int[] count = counts[i];

            for (int m = M; m >= 0; m--) {

                for (int n = N; n >= 0; n--) {

                    int ways1 = 0;
                    if (m + count[0] <= M && n + count[1] <= N) {
                        ways1 = 1 + dp[i + 1][m + count[0]][n + count[1]];
                    }

                    int ways2 = dp[i + 1][m][n];

                    dp[i][m][n] = Math.max(ways1, ways2);
                }

            }
        }

        return dp[0][0][0];
    }

    public static int dfs(int[][] counts, int M, int N, int m, int n, int i) {
        if (i >= counts.length) {
            return 0;
        }
        if (m > M || n > N) {
            return -1;
        }
        // 当前在i位置做决定,可以要i,也可以不要i
        int[] count = counts[i];

        int ways1 = dfs(counts, M, N, m + count[0], n + count[1], i + 1);

        int ways2 = dfs(counts, M, N, m, n, i + 1);

        if (ways1 >= 0) {
            ways1 += 1;
        }

        return Math.max(ways1, ways2);
    }

    public static int[][] zeroCount(String[] strs) {
        int[][] result = new int[strs.length][2];

        for (int i = 0; i < strs.length; i++) {
            int[] item = new int[2];
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (c == '0') {
                    item[0]++;
                } else {
                    item[1]++;
                }

            }
            result[i] = item;
        }

        return result;
    }


}
