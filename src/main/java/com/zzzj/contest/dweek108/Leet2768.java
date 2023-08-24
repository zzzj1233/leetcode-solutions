package com.zzzj.contest.dweek108;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet2768 {


    public static void main(String[] args) {

        System.out.println(Arrays.toString(countBlackBlocks(3, 3, LeetUtils.convertInts("[[0,0]]"))));

        System.out.println(Arrays.toString(countBlackBlocks(3, 3, LeetUtils.convertInts("[[0,0],[1,1],[0,2]]"))));

    }

    static int[][][] DIRS2 = {
            {
                    {0, 1},
                    {1, 0},
                    {1, 1}
            },
            {
                    {0, -1},
                    {1, 0},
                    {1, -1}
            },
            {
                    {-1, 0},
                    {-1, 1},
                    {0, 1}
            },
            {
                    {0, -1},
                    {-1, 0},
                    {-1, -1}
            }
    };

    public static long[] countBlackBlocks(int m, int n, int[][] coordinates) {

        Map<Integer, Set<Integer>> black = new HashMap<>();

        for (int[] coordinate : coordinates) {
            black.computeIfAbsent(coordinate[0], integer -> new HashSet<>())
                    .add(coordinate[1]);
        }

        long[] ans = new long[5];

        for (int[] coordinate : coordinates) {

            int r = coordinate[0];

            int c = coordinate[1];


            OUTER:
            for (int[][] DIRS : DIRS2) {

                int cnt = 1;

                for (int[] dir : DIRS) {

                    int row = dir[0] + r;

                    int col = dir[1] + c;

                    if (row < 0 || col < 0 || row >= m || col >= n)
                        continue OUTER;

                    cnt += black.getOrDefault(row, Collections.emptySet()).contains(col) ? 1 : 0;
                }

                ans[cnt]++;
            }

        }

        long totalCnt = (long) (m - 1) * (n - 1);

        ans[2] /= 2;
        ans[3] /= 3;
        ans[4] /= 4;

        ans[0] = totalCnt - ans[1] - ans[2] - ans[3] - ans[4];

        return ans;
    }

}
