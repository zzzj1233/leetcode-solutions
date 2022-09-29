package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-26 18:24
 */
public class Leet2083 {

    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcba"));
        System.out.println(numberOfSubstrings("bbbb"));
        System.out.println(numberOfSubstrings("abacad"));
    }

    public static long numberOfSubstrings(String s) {

        int N = s.length();

        long ans = 0;

        // 看看后面有多少个相同字母
        int[] bucket = new int[26];

        int[] dp = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            int count = ++bucket[s.charAt(i) - 'a'];
            dp[i] = count;
        }

        // bxxbxxb = 3
        // bxxbxxbxxb = 6

        boolean[] enable = new boolean[26];
        Arrays.fill(enable, true);

        for (int i = 0; i < N; i++) {
            int count = dp[i];
            if (!enable[s.charAt(i) - 'a']) {
                continue;
            }
            ans += ((long) (count + 1)) * count / 2;
            enable[s.charAt(i) - 'a'] = false;
        }

        return ans;
    }

}
