package com.zzzj.dp;

import java.util.BitSet;

/**
 * @author zzzj
 * @create 2023-08-25 18:19
 */
public class Leet2430 {


    public static void main(String[] args) {
        System.out.println(deleteString("abcabcdabc"));
    }

    public static int deleteString(String s) {

        int N = s.length();

        BitSet bitSet = new BitSet();

        int[] dp = new int[N];

        dp[0] = 1;

        for (int i = 1; i < N; i++) {

            dp[i] = dp[i - 1];

            for (int j = i - 1; j >= 0; j--) {
                int right = i + i - j - 1;
                if (right >= N) break;
                if (s.charAt(j) != s.charAt(right)) break;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

        }

        return dp[N - 1];
    }

}
