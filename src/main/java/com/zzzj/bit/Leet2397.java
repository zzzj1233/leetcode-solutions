package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-23 17:52
 */
public class Leet2397 {

    public static void main(String[] args) {

//        System.out.println(maximumRows(
//                LeetUtils.convertInts("[[0, 1, 0], [0, 0, 0]]"),
//                1
//        ));
//
//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int N = 10;

            int M = 10;

            int[][] ints = LeetUtils.randomMatrix(N, M, 0, 2);

            int numSelect = LeetUtils.random.nextInt(M) + 1;

            CopyableIterator<int[][]> iter = new CopyableIterator<>(ints, ArrayUtil::copy);

            if (maximumRows(iter.next(), numSelect) != right(iter.next(), numSelect)) {
                System.out.println(Arrays.deepToString(iter.next()));
                System.out.println(numSelect);
                System.out.println(maximumRows(iter.next(), numSelect));
                System.out.println(right(iter.next(), numSelect));
                return;
            }
        }

        System.out.println("OK");

    }

    public static int maximumRows(int[][] M, int numSelect) {
        // 覆盖了numSelect列, 这一行就没有1了, 那么这一行可以算作答案

        ans = 0;

        int N = M.length;

        int[] matrix = new int[N];

        int col = M[0].length;

        for (int i = 0; i < N; i++) {

            int stat = 0;

            for (int j = 0; j < col; j++) {

                if (M[i][j] == 1) {
                    stat |= 1 << j;
                }

            }

            matrix[i] = stat;
        }

        dfs(matrix, M, numSelect, 0, col);

        return ans;
    }


    static int ans;

    public static void dfs(int[] matrix, int[][] M, int numSelect, int colIndex, int col) {

        int N = matrix.length;

        if (numSelect == 0) {

            int cnt = 0;

            for (int i = 0; i < N; i++) {
                if (Integer.bitCount(matrix[i]) == 0) cnt++;
            }

            ans = Math.max(ans, cnt);

            return;
        }

        if (numSelect < (col - colIndex))
            dfs(matrix, M, numSelect, colIndex + 1, col);

        for (int i = 0; i < N; i++) {
            matrix[i] &= ~(1 << colIndex);
        }

        dfs(matrix, M, numSelect - 1, colIndex + 1, col);

        for (int i = 0; i < N; i++) {

            if (M[i][colIndex] == 1) {
                matrix[i] |= (1 << colIndex);
            }

        }

    }

    public static int right(int[][] matrix, int numSelect) {
        int n = matrix.length, m = matrix[0].length;
        int[] s = new int[n];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) s[i] |= matrix[i][j] << j;

        int ans = 0, tot = 1 << m;
        for (int i = 1; i < tot; i++) {
            if (Integer.bitCount(i) != numSelect) continue;
            int cnt = 0;
            for (int j = 0; j < n; j++) if ((s[j] & i) == s[j]) cnt++;
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

}
