package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-26 10:56
 */
public class Leet70 {


    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }

    public static int climbStairs(int n) {
        int[] memo = new int[n + 1];

        memo[0] = 1;
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }


}
