package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2024-01-16 15:48
 */
public class Leet960 {

    public static void main(String[] args) {

        System.out.println(minDeletionSize(new String[]{"edcba"}));

        System.out.println(minDeletionSize(new String[]{"babca", "bbazb"}));

    }

    public static int minDeletionSize(String[] strs) {

        int N = strs.length;

        int M = strs[0].length();

        int[][] f = new int[N][M];

        for (int i = 0; i < N; i++) {

            String str = strs[i];

            for (int j = 0; j < M; j++) {

                char c = str.charAt(j);

                f[i][j] = 1;

                OUTER:
                for (int k = 0; k < j; k++) {

                    if (str.charAt(k) > c) {
                        continue;
                    }

                    for (int x = 0; x < N; x++) {
                        if (strs[x].charAt(k) > strs[x].charAt(j))
                            continue OUTER;
                    }

                    f[i][j] = Math.max(
                            f[i][j],
                            f[i][k] + 1
                    );

                }

            }

        }

        int ans = Integer.MAX_VALUE;

        for (int j = 0; j < M; j++) {

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++)
                min = Math.min(min, f[i][j]);

            ans = Math.min(ans, j - min + (M - j));
        }

        return ans;
    }

}
