package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-27 16:16
 */
public class Leet2536 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(rangeAddQueries(2, LeetUtils.convertInts("[[0,0,1,1]]"))));

        // System.out.println(Arrays.deepToString(rangeAddQueries(3, LeetUtils.convertInts("[[1,1,2,2],[0,0,1,1]]"))));

    }

    public static int[][] rangeAddQueries(int n, int[][] queries) {

        int[][] wrapped = new int[n + 1][n + 1];

        int N = queries.length;

        int[][] diff = new int[n + 1][n + 1];

        for (int i = 0; i < N; i++) {
            int[] query = queries[i];
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];
            diff[r1][c1] += 1;
            diff[r2 + 1][c2 + 1] += 1;
            diff[r2 + 1][c1] -= 1;
            diff[r1][c2 + 1] -= 1;
        }

        int value = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                wrapped[i][j] = wrapped[i - 1][j] + wrapped[i][j - 1] - wrapped[i - 1][j - 1] + diff[i - 1][j - 1];
            }
        }

        int[][] ans = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = wrapped[i + 1][j + 1];
            }
        }

        return ans;
    }

}
