package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-09-29 17:02
 */
public class Leet552 {

    public static void main(String[] args) {
        System.out.println(checkRecord(10101));
    }

    @SuppressWarnings("OverlyComplexArithmeticExpression")
    public static int checkRecord(int n) {
        long[][][] dp = new long[n][2][3];

        int mod = 1000000007;

        // dp[天数][缺勤数][连续迟到数]

        // 没缺勤没迟到
        dp[0][0][0] = 1;
        // 迟到了
        dp[0][0][1] = 1;
        // 缺勤了
        dp[0][1][0] = 1;

        for (int i = 1; i < n; i++) {
            // 缺勤一次,迟到0次
            dp[i][1][0] = (
                    // 昨天缺勤一次,迟到0次
                    dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]
                            +
                            // 昨天缺勤0次,迟到0/1/2次
                            dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]

            ) % mod;

            // 缺勤一次,迟到1次
            dp[i][1][1] = dp[i - 1][1][0] % mod;

            // 缺勤一次,迟到2次
            dp[i][1][2] = dp[i - 1][1][1] % mod;

            // 没有缺勤,迟到1次
            dp[i][0][1] = dp[i - 1][0][0] % mod;

            // 没有缺勤,迟到2次
            dp[i][0][2] = dp[i - 1][0][1] % mod;

            // 没有缺勤,迟到0次
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % mod;

        }

        return (int) ((dp[n - 1][0][0] + dp[n - 1][0][1] + dp[n - 1][0][2]
                + dp[n - 1][1][0] + dp[n - 1][1][1] + dp[n - 1][1][2]) % mod);
    }


}
