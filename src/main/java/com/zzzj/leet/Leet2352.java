package com.zzzj.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-10-11 15:52
 */
public class Leet2352 {

    public static void main(String[] args) {
//        System.out.println(equalPairs(LeetUtils.convertInts("[[3,2,1],[1,7,6],[2,7,7]]")));
        System.out.println(equalPairs(LeetUtils.convertInts("[[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]")));
    }

    public static int equalPairs(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        Map<Integer, List<Integer>> sum = new HashMap<>(M);

        for (int i = 0; i < M; i++) {
            int value = 0;

            for (int j = 0; j < N; j++) {
                value += grid[i][j];
            }

            // 行号
            sum.computeIfAbsent(value, integer -> new ArrayList<>())
                    .add(i);
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int value = 0;

            for (int j = 0; j < M; j++) {
                value += grid[j][i];
            }

            List<Integer> list = sum.get(value);

            if (list != null) {

                OUTER:
                for (Integer rowIndex : list) {

                    for (int j = 0; j < N; j++) {
                        if (grid[rowIndex][j] != grid[j][i]){
                            continue OUTER;
                        }
                    }

                    ans++;
                    // System.out.println(rowIndex + " --- " + i);
                }

            }

        }

        return ans;
    }

}
