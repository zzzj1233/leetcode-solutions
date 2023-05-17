package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet1329 {

    public static void main(String[] args) {

        // 3, 9
        // 2, 4
        // 1, 2
        // 9, 8
        // 7, 3
        System.out.println(Arrays.deepToString(diagonalSort(
                LeetUtils.convertInts("[[3,9],[2,4],[1,2],[9,8],[7,3]]")
        )));

    }

    public static int[][] diagonalSort(int[][] mat) {

        int M = mat.length;
        int N = mat[0].length;

        // M = 3
        // N = 4

        int max = Math.max(M, N);

        // 0, 0 / 1, 1 / 2, 2
        // 0, 1 / 1, 2 / 2, 3
        // 0, 2 / 1, 3
        // 0, 3

        List<int[]> indexes = new ArrayList<>(max);

        for (int i = 0; i < max; i++) {

            for (int k = i, j = 0; k < N && j < M; k++, j++) {

                indexes.add(new int[]{j, k});

            }

            sort(indexes, mat);

            indexes.clear();

        }

        // 2, 0
        // 1, 0 / 2, 1
        // 0, 0 / 1, 1 / 2, 2

        for (int i = 0; i < max; i++) {

            for (int k = i, j = 0; k < M && j < N; k++, j++) {

                indexes.add(new int[]{k, j});

            }

            sort(indexes, mat);

            indexes.clear();

        }

        return mat;
    }

    public static void sort(List<int[]> indexes, int[][] mat) {

        int size = indexes.size();

        for (int i = 0; i < size; i++) {

            int minIndex = i;

            for (int j = i + 1; j < size; j++) {

                int[] index = indexes.get(j);

                if (mat[index[0]][index[1]] < mat[indexes.get(minIndex)[0]][indexes.get(minIndex)[1]]) {
                    minIndex = j;
                }

            }

            swap(indexes, mat, i, minIndex);

        }

    }

    public static void swap(List<int[]> indexes, int[][] mat, int x, int y) {
        if (x == y) {
            return;
        }

        int[] xIndex = indexes.get(x);

        int[] yIndex = indexes.get(y);

        int temp = mat[xIndex[0]][xIndex[1]];
        mat[xIndex[0]][xIndex[1]] = mat[yIndex[0]][yIndex[1]];
        mat[yIndex[0]][yIndex[1]] = temp;
    }

}
