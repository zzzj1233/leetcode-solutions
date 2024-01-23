package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-01-10 15:30
 */
public class Leet1639 {

    public static void main(String[] args) {

        System.out.println(numWays(new String[]{"acca", "bbbb", "caca"}, "aba"));

        System.out.println(numWays(new String[]{"abba", "baab"}, "bab"));

        System.out.println(numWays(new String[]{"abab", "baba", "abba", "baab"}, "abba"));

    }

    static final int MOD = 1000000007;

    public static int numWays(String[] words, String target) {

        int N = words.length;

        int M = target.length();

        int K = words[0].length();

        long[][] f = new long[K + 1][M + 1];

        f[0][0] = 1;

        int[][] tab = new int[K + 1][26];

        for (int k = 1; k <= K; k++)
            for (String word : words)
                tab[k][word.charAt(k - 1) - 'a']++;

        for (int k = 1; k <= K; k++) {

            for (int i = 1; i <= M; i++) {

                f[k][0] = 1;
                f[k][i] = f[k - 1][i];

                char c = target.charAt(i - 1);

                f[k][i] = (f[k][i] + (f[k - 1][i - 1] * tab[k][c - 'a']) % MOD) % MOD;
            }

        }

        return (int) f[K][M] % MOD;
    }

}
