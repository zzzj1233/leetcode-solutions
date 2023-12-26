package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-12-14 11:00
 */
public class Leet546 {

    public static void main(String[] args) {

        System.out.println(removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));

        System.out.println(removeBoxes(new int[]{1, 1, 1}));

        System.out.println(removeBoxes(new int[]{1}));

        // System.out.println(f[2][5][maxIndex]);
    }

    public static int removeBoxes(int[] boxes) {

        int N = boxes.length;

        int[][][] f = new int[N][N][N + 2];

        int maxIndex = N + 1;

        for (int i = 0; i < N; i++) {
            f[i][i][1] = 1;
            f[i][i][maxIndex] = 1;
        }

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                int cnt = 1;

                for (int k = left; k < right; k++) {
                    f[left][right][1] = Math.max(
                            f[left][right][1],
                            f[left][k][maxIndex] + f[k + 1][right][maxIndex]
                    );
                    f[left][right][maxIndex] = Math.max(
                            f[left][right][maxIndex],
                            f[left][right][1]
                    );
                }

                for (int k = left; k < right; k++) {

                    if (boxes[k] == boxes[right]) {

                        cnt++;

                        for (int i = 2; i <= cnt; i++) {
                            f[left][right][i] = Math.max(
                                    f[left][right][i],
                                    f[left][k][i - 1] + f[k + 1][right - 1][maxIndex] + i * i - ((i - 1) * (i - 1))
                            );

                            f[left][right][maxIndex] = Math.max(
                                    f[left][right][maxIndex],
                                    f[left][right][i]
                            );
                        }

                    }

                }

            }

        }

        // System.out.println("ans : " + f[0][N - 1][maxIndex]);

        return f[0][N - 1][maxIndex];
    }

}
