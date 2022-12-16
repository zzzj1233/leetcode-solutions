package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-11-23 17:52
 */
public class Leet2397 {

    public static void main(String[] args) {
        System.out.println(maximumRows(LeetUtils.convertInts("[[0,0,0],[1,0,1],[0,1,1],[0,0,1]]"), 2));
    }

    public static int maximumRows(int[][] matrix, int numSelect) {
        int n = matrix.length, m = matrix[0].length;

        int[] s = new int[n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                s[i] |= matrix[i][j] << j;
            }

        }

        int ans = 0, tot = 1 << m;

        for (int i = 1; i < tot; i++) {

            if (Integer.bitCount(i) != numSelect) {
                continue;
            }

            int cnt = 0;

            for (int j = 0; j < n; j++) {
                if ((s[j] & i) == s[j]) {
                    cnt++;
                }
            }

            ans = Math.max(ans, cnt);
        }

        return ans;
    }

}
