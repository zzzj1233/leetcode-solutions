package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-05 21:57
 */
public class Leet62 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {
        return dfs(m, n, 0, 0);
    }

    public static int dfs(int M, int N, int i, int j) {
        if (i == M - 1 && j == N - 1) {
            return 1;
        }

        int bottom = 0;

        int right = 0;

        if (i + 1 < M) {
            bottom = dfs(M, N, i + 1, j);
        }

        if (j + 1 < N) {
            right = dfs(M, N, i, j + 1);
        }

        return Math.max(bottom, right) + 1;
    }

}
