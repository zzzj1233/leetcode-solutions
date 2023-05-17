package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-22 18:54
 */
public class Leet1638 {

    public static void main(String[] args) {

        System.out.println(countSubstrings("aba", "baba"));

        System.out.println(countSubstrings("abe", "bbc"));

    }

    public static int countSubstrings(String s, String t) {

        int M = s.length();

        int N = t.length();

        // 1. 长度相同
        // 2. 只有一个字符不同
        int ans = 0;

        for (int i = 0; i < M; i++) {

            for (int j = i; j < M; j++) {

                int len = j - i;

                for (int k = 0; k + len < N; k++) {

                    if (match(s, i, j, t, k, k + len)) {
                        ans++;
                    }

                }

            }

        }

        return ans;
    }

    public static boolean match(
            String s, int x, int y,
            String t, int k, int v
    ) {


        int len = y - x;

        boolean anyDiff = false;

        for (int i = 0; i <= len; i++) {
            if (s.charAt(x + i) != t.charAt(k + i)) {
                if (anyDiff) return false;
                anyDiff = true;
            }
        }

        return anyDiff;
    }
}
