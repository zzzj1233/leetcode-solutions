package com.zzzj.graph;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-09-25 22:07
 */
public class Leet1222 {

    public static final int[][] DIRS2 = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        // 从king向八个方向出发
        List<List<Integer>> ans = new ArrayList<>(8);

        Map<Integer, Set<Integer>> map = new HashMap<>(queens.length);

        for (int[] queen : queens) {
            map.computeIfAbsent(queen[0], integer -> new HashSet<>())
                    .add(queen[1]);
        }

        for (int[] dir : DIRS2) {
            int row = dir[0];
            int col = dir[1];
            dfs(ans, king[0], king[1], map, row, col);
        }

        return ans;
    }

    public static void dfs(List<List<Integer>> ans, int x, int y, Map<Integer, Set<Integer>> map, int row, int col) {
        if (x < 0 || y < 0 || x >= 8 || y >= 8) {
            return;
        }

        Set<Integer> set = map.get(x);

        if (set != null && set.contains(y)) {
            ans.add(Arrays.asList(x, y));
            return;
        }


        dfs(ans, x + row, y + col, map, row, col);
    }


}
