package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-06-21 17:08
 */
public class Leet994 {

    public static void main(String[] args) {
        System.out.println(orangesRotting(LeetUtils.convertInts("[[2,1,1],[1,1,0],[0,1,1]]")));
    }

    public static int orangesRotting(int[][] grid) {
        // 0 = 空格
        // 1 = 新鲜橘子
        // 2 = 腐烂橘子

        LinkedList<int[]> list = new LinkedList<>();

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int freshCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    list.add(new int[]{i, j});
                    visited[i][j] = true;
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        if (list.isEmpty()) {
            return -1;
        }

        return bfs(grid, list, visited, freshCount);
    }

    public static int bfs(int[][] grid, LinkedList<int[]> list, boolean[][] visited, int freshCount) {
        int level = 0;

        int count = freshCount;

        while (!list.isEmpty()) {
            int size = list.size();

            level++;

            for (int i = 0; i < size; i++) {
                int[] first = list.removeFirst();
                int x = first[0];
                int y = first[1];
                // 四周
                if (pick(x + 1, y, list, visited, grid)) {
                    count--;
                }
                if (pick(x - 1, y, list, visited, grid)) {
                    count--;
                }
                if (pick(x, y + 1, list, visited, grid)) {
                    count--;
                }
                if (pick(x, y - 1, list, visited, grid)) {
                    count--;
                }
                if (count == 0) {
                    return level;
                }
            }
        }

        return count == 0 ? level : -1;
    }

    public static boolean pick(int i, int j, LinkedList<int[]> list, boolean[][] visited, int[][] grid) {
        if (i < 0 || j < 0 || i >= visited.length || j >= visited[i].length || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (grid[i][j] != 0) {
            list.addLast(new int[]{i, j});
            return true;
        }
        return false;
    }

}
