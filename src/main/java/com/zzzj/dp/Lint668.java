package com.zzzj.dp;

public class Lint668 {

    public static void main(String[] args) {

        System.out.println(findMaxForm(
                new String[]{"10", "0001", "111001", "1", "0"},
                5,
                3
        ));

    }

    public static int findMaxForm(String[] strs, int m, int n) {

        int N = strs.length;

        int[][] w = new int[N][2];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < strs[i].length(); j++)
                if (strs[i].charAt(j) == '0')
                    w[i][0]++;
                else
                    w[i][1]++;

        int[][] f = new int[m + 1][n + 1];

        for (int i = 0; i < N; i++)
            for (int v1 = m; v1 >= w[i][0]; v1--)
                for (int v2 = n; v2 >= w[i][1]; v2--)
                    f[v1][v2] = Math.max(
                            f[v1][v2],
                            f[v1 - w[i][0]][v2 - w[i][1]] + 1
                    );

        return f[m][n];
    }

}
