package com.zzzj.contest.week343;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-02 11:21
 */
public class Leet2661 {

    public static void main(String[] args) {

        System.out.println(firstCompleteIndex(new int[]{1, 3, 4, 2}, LeetUtils.convertInts("[[1,4],[2,3]]")));

        System.out.println(firstCompleteIndex(new int[]{2, 8, 7, 4, 1, 3, 5, 6, 9}, LeetUtils.convertInts("[[3,2,5],[1,4,6],[8,7,9]]")));

    }

    public static int firstCompleteIndex(int[] arr, int[][] mat) {

        int M = mat.length;

        int N = mat[0].length;

        int[] row = new int[M];
        int[] col = new int[N];

        Arrays.fill(row, N);
        Arrays.fill(col, M);

        Map<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                map.put(mat[i][j], new int[]{i, j});

            }

        }

        for (int i = 0; i < arr.length; i++) {
            int it = arr[i];

            int[] items = map.get(it);
            int r = items[0];
            int c = items[1];

            if (--row[r] == 0 || --col[c] == 0) {
                return i;
            }

        }

        return -1;
    }

}
