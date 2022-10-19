package com.zzzj.leet;

public class Leet2414 {

    public static int longestContinuousSubstring(String s) {

        int N = s.length();

        if (N == 0) {
            return 0;
        }

        int ans = 1;

        int step = 0;

        for (int i = 1; i < N; i++) {
            if (s.charAt(i) == s.charAt(i - 1) + 1) {
                step++;
            } else {
                step = 1;
            }
            ans = Math.max(ans, step);
        }

        return ans;
    }

}
