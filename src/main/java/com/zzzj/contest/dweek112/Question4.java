package com.zzzj.contest.dweek112;

import java.util.Arrays;

public class Question4 {

    public static void main(String[] args) {

//        System.out.println(countKSubsequencesWithMaxBeauty("bcca", 2));
//
        System.out.println(countKSubsequencesWithMaxBeauty("abbcd", 2));
//
//        System.out.println(countKSubsequencesWithMaxBeauty("minc", 3));

    }

    public static int countKSubsequencesWithMaxBeauty(String s, int k) {

        if (k > 26)
            return 0;

        int N = s.length();

        if (N == 0)
            return 0;

        int[] tab = new int[26];

        for (int i = 0; i < N; i++) {
            tab[s.charAt(i) - 'a']++;
        }

        Arrays.sort(tab);

        int high = 0;

        for (int i = 25, x = k; i >= 0 && x > 0; i--, x--) {
            high += tab[i];
        }

        if (k == 1) {
            int finalHigh = high;
            return Arrays.stream(tab).filter(value -> value == finalHigh).sum();
        }

        // 11122

        int index = 0;

        while (tab[index] == 0) index++;

        int dpIndex = 1;

        int[] dp = new int[high + 1];

        dp[0] = 1;

        for (int i = dpIndex; i < k; i++, index++) {

            int score = tab[index];

            for (int j = high; j - score >= 0; j--) {
                dp[j] += dp[j - score];
            }

        }

        int ans = 0;

        for (int i = index; i < 26; i++) {

            int score = tab[i];

            System.out.println("dp = " + Arrays.toString(dp));

            // 凑出dp[k][high] = 方案数
            ans += dp[high - score] * score;

            for (int j = high; j - score >= 0; j--) {
                dp[j] += dp[j - score];
            }

        }

        return ans;
    }

}
