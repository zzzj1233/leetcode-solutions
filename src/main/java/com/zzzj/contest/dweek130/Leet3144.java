package com.zzzj.contest.dweek130;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-05-31 11:45
 */
public class Leet3144 {

    public static void main(String[] args) {

        System.out.println(minimumSubstringsInPartition("fabccddg"));

        System.out.println(minimumSubstringsInPartition("abababaccddb"));

    }

    public static int minimumSubstringsInPartition(String s) {

        int N = s.length();

        int[][] ps = new int[N + 1][26];

        for (int i = 1; i <= N; i++) {

            int index = s.charAt(i - 1) - 'a';

            for (int j = 0; j < 26; j++)
                ps[i][j] = ps[i - 1][j];

            ps[i][index]++;

        }

        int[] f = new int[N + 1];

        Arrays.fill(f, Integer.MAX_VALUE);

        f[0] = 0;

        for (int i = 1; i <= N; i++) {

            f[i] = f[i - 1] + 1;

            for (int j = i - 1; j >= 0; j--) {

                int[] t1 = ps[i];
                int[] t2 = ps[j];

                if (check(t1, t2))
                    f[i] = Math.min(
                            f[i],
                            f[j] + 1
                    );

            }

        }

        return f[N];
    }

    public static boolean check(int[] t1, int[] t2) {
        int cnt = -1;
        for (int i = 0; i < 26; i++)
            if (t1[i] != t2[i])
                if (cnt == -1)
                    cnt = t1[i] - t2[i];
                else if (t1[i] - t2[i] != cnt)
                    return false;
        return true;
    }

}
