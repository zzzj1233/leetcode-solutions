package com.zzzj.leet;


/**
 * @author zzzj
 * @create 2022-05-20 20:26
 */
public class Leet474 {

    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 3, 4));
    }

    public static int findMaxForm(String[] strs, int m, int n) {

        int N = strs.length;

        int[][] w = new int[N][2];

        for (int i = 0; i < N; i++) {
            String s = strs[i];
            int M = s.length();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '0')
                    w[i][0]++;
                else
                    w[i][1]++;
            }
        }

        int[][] f = new int[m + 1][n + 1];

        for (int i = 0; i < N; i++) {

            int z = w[i][0];

            int o = w[i][1];

            for (int v1 = m; v1 >= z; v1--) {

                for (int v2 = n; v2 >= o; v2--) {

                    f[v1][v2] = Math.max(
                            f[v1][v2],
                            f[v1 - z][v2 - o] + 1
                    );

                }


            }

        }

        return f[m][n];
    }

}
