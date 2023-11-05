package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-06-25 16:56
 */
public class Leet2257 {

    public static void main(String[] args) {
        System.out.println(countUnguarded(4, 6, LeetUtils.convertInts("[[0,0],[1,1],[2,3]]"), LeetUtils.convertInts("[[0,1],[2,2],[1,4]]")));
    }

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {

        int total = m * n - guards.length - walls.length;

        Map<Integer, Set<Integer>> guardsMap = new HashMap<>();

        Map<Integer, Set<Integer>> wallsMap = new HashMap<>();

        for (int[] guard : guards) {
            guardsMap.computeIfAbsent(guard[0], integer -> new HashSet<>())
                    .add(guard[1]);
        }

        for (int[] wall : walls) {
            wallsMap.computeIfAbsent(wall[0], integer -> new HashSet<>())
                    .add(wall[1]);
        }

        boolean[][] visited = new boolean[m][n];

        int cnt = 0;

        for (int[] guard : guards) {
            cnt += dfs(m, n, guard[0], guard[1], guardsMap, wallsMap, visited);
        }

        return total - cnt;
    }

    static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static int dfs(int m, int n,
                          int x, int y,
                          Map<Integer, Set<Integer>> guardsMap,
                          Map<Integer, Set<Integer>> wallsMap,
                          boolean[][] visited) {
        int cnt = 0;

        for (int[] dir : DIRS) {

            int r = x + dir[0];

            int c = y + dir[1];

            while (
                    r >= 0 && c >= 0 && r < m && c < n
                            && !guardsMap.getOrDefault(r, Collections.emptySet()).contains(c)
                            && !wallsMap.getOrDefault(r, Collections.emptySet()).contains(c)
            ) {
                cnt += visited[r][c] ? 0 : 1;
                visited[r][c] = true;
                r += dir[0];
                c += dir[1];
            }
        }

        return cnt;
    }

}
