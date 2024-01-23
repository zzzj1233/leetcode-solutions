package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-01-22 11:24
 */
public class Leet296 {

    public static void main(String[] args) {

        System.out.println(minTotalDistance(LeetUtils.convertInts("[[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]")));

    }

    public static int minTotalDistance(int[][] grid) {

        List<Integer> x = new ArrayList<>();

        List<Integer> y = new ArrayList<>();

        int N = grid.length;

        int M = grid[0].length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }
            }
        }

        x.sort(Comparator.comparingInt(o -> o));
        y.sort(Comparator.comparingInt(o -> o));

        int ansX = x.get(x.size() / 2);
        int ansY = y.get(y.size() / 2);

        int ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    ans += Math.abs(ansX - i) + Math.abs(ansY - j);
                }
            }
        }

        return ans;
    }

}
