package com.zzzj.contest.dweek106;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-07-27 16:41
 */
public class Leet2732 {

    public static void main(String[] args) {

        System.out.println(goodSubsetofBinaryMatrix(LeetUtils.convertInts("[[0,1,1,0],[0,0,0,1],[1,1,1,1]]")));

        System.out.println(goodSubsetofBinaryMatrix(LeetUtils.convertInts("[[1,1,1],[1,1,1]]")));

        System.out.println(goodSubsetofBinaryMatrix(LeetUtils.convertInts("[[0,1,0,1,0],[0,1,1,1,0],[1,0,1,1,1],[0,0,1,1,1],[0,0,0,1,1],[1,1,1,0,0]]")));

    }

    public static List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        if (M == 1) {
            return Arrays.stream(grid[0]).sum() == 0 ? Arrays.asList(0) : Collections.emptyList();
        }

        int[][] combined = new int[M][2];

        for (int i = 0; i < M; i++) {

            combined[i][0] = i;

            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1)
                    combined[i][1] |= 1 << j;
            }

        }

        Arrays.sort(combined, Comparator.comparingInt(o -> Integer.bitCount(o[1])));

        List<Integer> ans = new ArrayList<>(2);

        for (int i = 0; i < M; i++) {

            if (i > 0 && Integer.bitCount(combined[i][1]) != Integer.bitCount(combined[i - 1][1])) {
                break;
            }

            for (int j = i + 1; j < M; j++) {
                if ((combined[i][1] & combined[j][1]) == 0) {
                    ans.add(combined[i][0]);
                    ans.add(combined[j][0]);
                    ans.sort(Comparator.comparingInt(o -> o));
                    return ans;
                }

            }
        }

        return Collections.emptyList();
    }

}
