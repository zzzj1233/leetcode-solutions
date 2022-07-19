package com.zzzj.leet;


/**
 * @author zzzj
 * @create 2022-05-20 20:26
 */
public class Leet474 {

    public static void main(String[] args) {
        // ["10","0001","111001","1","0"]
        // 3
        // 4
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 3, 4));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] counts = zeroCount(strs);

        // 最大子集
        int N = strs.length;

        int[][][] dp = new int[N][m + 1][n + 1];

        int ans = 0;

        if (counts[0][0] <= m && counts[0][1] <= n) {
            dp[0][counts[0][0]][counts[0][1]] = 1;
            ans = 1;
        }

        for (int i = 1; i < N; i++) {
            int[] count = counts[i];
            int zero = count[0];
            int one = count[1];

            dp[i] = dp[i - 1];

            if (zero > m || one > n) {
                continue;
            }

            int zeroSub = m - zero;
            int oneSub = n - one;

            for (int j = zeroSub; j >= 0; j--) {
                for (int k = oneSub; k >= 0; k--) {
                    dp[i][j + zero][k + one] = Math.max(dp[i - 1][j][k] + 1, dp[i][j + zero][k + one]);
                    ans = Math.max(ans, dp[i][j + zero][k + one]);
                }
            }


        }

        return ans;
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
