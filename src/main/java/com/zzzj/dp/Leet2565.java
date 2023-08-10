package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-08-07 14:32
 */
public class Leet2565 {

    public static void main(String[] args) {

//        System.out.println(minimumScore("abacaba", "bzaa"));
//
//        System.out.println(minimumScore("cde", "xyz"));
//
//        // 4
//        System.out.println(minimumScore("ac", "dacd"));

        System.out.println(minimumScore("adebddaccdcabaade", "adbae"));

    }

    // 只能删一次
    public static int minimumScore(String s, String t) {

        int N = s.length();

        int M = t.length();

        int[] prefix = new int[N + 1];

        for (int i = 1, j = 0; i <= N; i++) {
            prefix[i] = prefix[i - 1];
            if (j < M && s.charAt(i - 1) == t.charAt(j)) {
                prefix[i]++;
                j++;
            }
        }

        int[] suffix = new int[N + 1];

        for (int i = N - 1, j = M - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1];

            if (j >= 0 && s.charAt(i) == t.charAt(j)) {
                suffix[i]++;
                j--;
            }

        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= N; i++) {

            int matchCount = prefix[i] + suffix[i];

            ans = Math.min(ans, Math.max(0, M - matchCount));

        }

        return ans;
    }

}
