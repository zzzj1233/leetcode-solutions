package com.zzzj.dp;

public class Leet2478 {

    public static int beautifulPartitions(String s, int k, int minLength) {

        int N = s.length();

        int[][] dp = new int[N][k + 1];

        int startEnd = N - (k - 1) * minLength - 1;

        for (int i = 0; i < startEnd; i++) {

        }

        return -1;
    }

}
