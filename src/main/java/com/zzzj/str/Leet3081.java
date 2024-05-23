package com.zzzj.str;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-03-21 16:26
 */
public class Leet3081 {

    public static void main(String[] args) {


//        System.out.println(minimizeStringValue("abcdefghijklmnopqrstuvwxy??"));
//
//        System.out.println(minimizeStringValue("???"));
//
//        System.out.println(minimizeStringValue("a?a?"));

    }

    public static String minimizeStringValue(String s) {

        int N = s.length();

        int[][] f = new int[N + 1][26];

        int[][][] choose = new int[N + 1][26][26];

        int[][] ps = new int[N + 1][26];

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j < 26; j++)
                ps[i][j] = ps[i - 1][j];

            if (s.charAt(i - 1) != '?')
                ps[i][s.charAt(i - 1) - 'a'] += 1;

        }

        for (int i = N - 1; i >= 0; i--) {

            if (s.charAt(i) != '?') {
                f[i] = f[i + 1];
                continue;
            }

            // 选j
            for (int j = 0; j < 26; j++) {

                int min = Integer.MAX_VALUE;

                int minIndex = -1;

                int left = ps[N][j] - ps[i + 1][j];

                for (int prev = 0; prev < 26; prev++) {
                    if (f[i + 1][prev] + choose[i + 1][prev][j] + left < min) {
                        min = f[i + 1][prev] + choose[i + 1][prev][j] + left;
                        minIndex = prev;
                    }
                }

                for (int k = 0; k < 26; k++) {
                    choose[i][j][k] = choose[i + 1][minIndex][k];
                }

                choose[i][j][j] += 1;
            }

        }

        // 根据f反推字符串
        char[] chars = s.toCharArray();

        for (int i = 0; i < N; i++) {

            if (s.charAt(i - 1) != '?')
                continue;

            int minScore = Arrays.stream(f[i]).min().orElse(0);

            for (int j = 0; j < 26; j++) {

                if (f[i][j] == minScore) {

                }

            }

        }

        return null;
    }

}
