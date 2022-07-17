package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author Zzzj
 * @create 2022-06-19 21:44
 */
public class Leet926 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString(100, "01");
            if (minFlipsMonoIncr(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int minFlipsMonoIncr(String s) {
        char[] chars = s.toCharArray();

        int N = chars.length;

        int[][] dp = dp(chars);

        return Math.min(dp[0][0], dp[0][1]);
    }

    public static int[][] dp(char[] chars) {
        int N = chars.length;
        int[][] dp = new int[N][2];

        dp[N - 1][0] = chars[N - 1] == '1' ? 1 : 0;
        dp[N - 1][1] = chars[N - 1] == '1' ? 0 : 1;

        for (int i = N - 2; i >= 0; i--) {
            char cur = chars[i];

            if (cur == '1') {
                // 当前字符 == '1'
                dp[i][1] = dp[i + 1][1];
                dp[i][0] = Math.min(1 + dp[i + 1][0], dp[i + 1][1]);
            } else {
                // 当前字符 == '0'
                dp[i][1] = 1 + dp[i + 1][1];
                dp[i][0] = Math.min(dp[i + 1][0], 1 + dp[i + 1][1]);
            }
        }

        return dp;
    }

    public static int dfs(char[] chars, char per, int i, int[][] cache) {
        if (i >= chars.length) {
            return 0;
        }

        int index = per == '1' ? 1 : 0;

        if (cache[i][index] != -1) {
            return cache[i][index];
        }

        if (chars[i] == '1') {
            if (per == '1') {
                cache[i][1] = dfs(chars, per, i + 1, cache);
            } else {
                // 当前字符反转为0
                int ways1 = 1 + dfs(chars, '0', i + 1, cache);
                // 剩下字符反转为1
                int ways2 = dfs(chars, '1', i + 1, cache);
                cache[i][0] = Math.min(ways1, ways2);
            }
        } else {
            // 当前字符是0
            if (per == '1') {
                // 当前字符必须反转
                cache[i][1] = 1 + dfs(chars, '1', i + 1, cache);
            } else {
                // 上一个字符也是0
                int ways1 = dfs(chars, '0', i + 1, cache);
                int ways2 = 1 + dfs(chars, '1', i + 1, cache);
                cache[i][0] = Math.min(ways1, ways2);
            }
        }
        return cache[i][index];
    }

    public static int right(String s) {
        int t = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            t += arr[i] == '1' ? 1 : 0;
        }
        int ans = t;
        for (int i = arr.length - 1; i >= 0; i--) {
            t += arr[i] == '0' ? 1 : -1;
            ans = Math.min(ans, t);
        }
        return ans;
    }

}
