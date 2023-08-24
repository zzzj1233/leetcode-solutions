package com.zzzj.contest.week347;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Leet2711 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(differenceOfDistinctValues(LeetUtils.convertInts("[[1,2,3],[3,1,5],[3,2,1]]"))));

    }

    public static int[][] differenceOfDistinctValues(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int[][] ans = new int[M][N];

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                Set<Integer> set = new HashSet<>();

                int r = i - 1;
                int c = j - 1;

                while (r >= 0 && c >= 0) {
                    set.add(grid[r][c]);
                    r--;
                    c--;
                }

                int topLeft = set.size();
                set.clear();

                r = i + 1;
                c = j + 1;

                while (r < M && c < N) {
                    set.add(grid[r][c]);
                    r++;
                    c++;
                }

                ans[i][j] = Math.abs(topLeft - set.size());
            }

        }

        return ans;
    }

}
