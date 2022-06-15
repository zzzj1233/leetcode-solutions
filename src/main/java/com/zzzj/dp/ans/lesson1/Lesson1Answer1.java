package com.zzzj.dp.ans.lesson1;

/**
 * @author Zzzj
 * @create 2022-06-14 21:34
 */
public class Lesson1Answer1 {

    public static void main(String[] args) {

        System.out.println(question(5, 2, 4, 6));

        System.out.println(right(5, 2, 4, 6));

        //        for (int i = 0; i < 100; i++) {
//            int N = LeetUtils.random.nextInt(100) + 1;
//            int start = LeetUtils.random.nextInt(N);
//            int aim = LeetUtils.random.nextInt(N);
//            int step = LeetUtils.random.nextInt(100) + 1;
//            if (question(N, start, aim, step) != right(N, start, aim, step)) {
//                System.out.println("Error");
//                return;
//            }
//        }
//
//        System.out.println("Ok");
    }


    public static int question(int N, int start, int aim, int step) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || step < 1) {
            return -1;
        }
        return dp1(N, start, aim, step);
    }

    public static int dfs1(int N, int cur, int aim, int step) {
        if (step == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return dfs1(N, 2, aim, step - 1);
        }
        if (cur == N) {
            return dfs1(N, N - 1, aim, step - 1);
        }
        return dfs1(N, cur - 1, aim, step - 1) + dfs1(N, cur + 1, aim, step - 1);
    }

    public static int dp1(int N, int cur, int aim, int step) {
        int[][] dp = new int[N + 1][step + 1];

        dp[aim][0] = 1;


        for (int j = 1; j <= step; j++) {
            dp[1][j] = dp[2][j - 1];
            for (int i = 2; i < N; i++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1];
            }
            dp[N][j] = dp[N - 1][j - 1];
        }

        return dp[cur][step];
    }

    public static int right(int N, int start, int aim, int step) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || step < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][step + 1];

        dp[aim][0] = 1;

        for (int j = 1; j <= step; j++) {
            dp[1][j] = dp[2][j - 1];

            for (int i = 2; i < N; i++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1];
            }

            dp[N][j] = dp[N - 1][j - 1];
        }
        return dp[start][step];
    }


}
