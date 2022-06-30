package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-24 15:19
 */
public class Leet531 {

    public static int findLonelyPixel(char[][] picture) {
        int N = picture.length;
        int M = picture[0].length;

        int[] row = new int[N];
        int[] col = new int[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (picture[i][j] == 'B' && row[i] == 1 && col[j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }

}
