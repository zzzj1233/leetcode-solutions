package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-09-20 16:25
 */
public class Leet2503 {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(maxPoints(LeetUtils.convertInts("[[1,2,3],[2,5,7],[3,5,1]]"), new int[]{5, 6, 2})));

        System.out.println(Arrays.toString(maxPoints(LeetUtils.convertInts("[[420766,806051,922751],[181527,815280,904568],[952102,4037,140319],[324081,17907,799523],[176688,90257,83661],[932477,621193,623068],[135839,554701,511427],[227575,450848,178065],[785644,204668,835141],[313774,167359,501496],[641317,620688,74989],[324499,122376,270369],[2121,887154,848859],[456704,7763,662087],[286827,145349,468865],[277137,858176,725551],[106131,93684,576512],[372563,944355,497187],[884187,600892,268120],[576578,515031,807686]]"), new int[]{352655, 586228, 169685, 541073, 584647, 413832, 576537, 616413})));

    }

    public static int[] maxPoints(int[][] grid, int[] queries) {

        int M = grid.length;
        int N = grid[0].length;

        int K = queries.length;

        int[] ans = new int[K];

        int[][] combined = new int[K][2];

        for (int i = 0; i < K; i++) {
            combined[i][0] = queries[i];
            combined[i][1] = i;
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<int[]> queue = new PriorityQueue<>(N * M, Comparator.comparingInt(o -> grid[o[0]][o[1]]));

        queue.add(new int[]{0, 0});

        int index = 0;

        boolean[][] seen = new boolean[M][N];
        seen[0][0] = true;

        int cnt = 0;

        final int[][] DIRS = {
                {0, 1},
                {1, 0},
                {-1, 0},
                {0, -1}
        };

        while (!queue.isEmpty() && index < K) {

            int[] peek = queue.peek();

            if (grid[peek[0]][peek[1]] >= combined[index][0]) {
                ans[combined[index][1]] = cnt;
                index++;
                continue;
            }

            int[] poll = queue.poll();

            int x = poll[0];
            int y = poll[1];

            cnt++;

            for (int[] dir : DIRS) {
                int r = dir[0] + x;
                int c = dir[1] + y;

                if (r >= 0 && r < M && c >= 0 && c < N && !seen[r][c]) {
                    queue.add(new int[]{r, c});
                    seen[r][c] = true;
                }

            }

        }

        for (int i = index; i < K; i++)
            ans[combined[i][1]] = cnt;

        return ans;
    }

}
