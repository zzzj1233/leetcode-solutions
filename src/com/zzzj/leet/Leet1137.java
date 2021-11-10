package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-28 11:26
 */
public class Leet1137 {

    /**
     * 泰波那契序列Tn定义如下：
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 4
     * 输出：4
     * 解释：
     * T_3 = 0 + 1 + 1 = 2
     * T_4 = 1 + 1 + 2 = 4
     */

    public static void main(String[] args) {
        System.out.println(tribonacci(3));
    }

    public static int tribonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;

        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 3] + memo[i - 2] + memo[i - 1];
        }

        return memo[n];
    }

}
