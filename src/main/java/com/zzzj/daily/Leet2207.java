package com.zzzj.daily;

public class Leet2207 {

    public static void main(String[] args) {
        System.out.println(maximumSubsequenceCount("abdcdbc", "ac"));
    }

    public static long maximumSubsequenceCount(String text, String pattern) {

        char c0 = pattern.charAt(0);
        char c1 = pattern.charAt(1);

        int N = text.length();

        long ans = 0;

        int[] left = new int[N + 1];
        int[] right = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            right[i] = right[i + 1] + (text.charAt(i) == c1 ? 1 : 0);
        }


        int c0Count = 0;
        int c1Count = 0;

        for (int i = 0; i < N; i++) {
            if (text.charAt(i) == c0) {
                ans += right[i];
                c0Count++;
            } else if (text.charAt(i) == c1) {
                c1Count++;
            }
        }


        return c1 == c0 ? ans : ans + Math.max(c0Count, c1Count);
    }

}
