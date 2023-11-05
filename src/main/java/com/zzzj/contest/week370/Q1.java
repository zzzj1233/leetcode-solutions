package com.zzzj.contest.week370;

import com.zzzj.leet.LeetUtils;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(findChampion(LeetUtils.convertInts("[[0,1],[0,0]]")));

        System.out.println(findChampion(LeetUtils.convertInts("[[0,0,1],[1,0,1],[0,0,0]]")));

    }

    public static int findChampion(int[][] grid) {

        int N = grid.length;

        OUTER:
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                if (i == j)
                    continue;

                if (grid[j][i] == 1)
                    continue OUTER;
            }

            return i;
        }

        return -1;
    }

}
