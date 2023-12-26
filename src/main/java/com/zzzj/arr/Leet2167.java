package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-11-13 15:20
 */
public class Leet2167 {

    public static void main(String[] args) {

        System.out.println(minimumTime("0000011000"));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int M = 8;

            String str = LeetUtils.randomString(M, "01");

            int r = minimumTime(str);

            int rr = right(str);

            if (r != rr) {
                System.out.println("Error");
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("str = " + LeetUtils.stringToLeetCode(str));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int minimumTime(String s) {

        int N = s.length();

        TreeSet<Integer> indexes = new TreeSet<>();

        for (int i = 0; i < N; i++) if (s.charAt(i) == '1') indexes.add(i);

        int ans = indexes.size() << 1;

        for (int i = 0; i < N; i++) {

            if (s.charAt(i) != '1') continue;

            Integer prev = indexes.floor(i - 1);

            Integer next = indexes.ceiling(i + 1);

            if (prev == null)
                prev = -1;
            if (next == null)
                next = N;

            // i + 1: 清除左边到当前元素的所有
            int case1 = i + 1 + N - next;

            int case2 = prev + 1 + N - i;

            int case3 = prev + 1 + (N - next) + 2;

            ans = Math.min(
                    Math.min(case1, case2),
                    Math.min(case3, ans)
            );
        }

        return ans;
    }

    public static int right(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][3];
        for (int i = 1; i <= n; ++i) {
            int curVal = s.charAt(i - 1) - '0';
            dp[i][0] = dp[i - 1][0] + 1;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 2 * curVal;
            dp[i][2] = Math.min(Math.min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]) + 1;
        }

        return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]);
    }

}
