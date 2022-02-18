package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-28 11:22
 */
public class Leet509 {

    public static void main(String[] args) {
        System.out.println(fib(3));
        System.out.println(fib(4));
    }

    public static int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] memo = new int[n + 1];
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }

}
