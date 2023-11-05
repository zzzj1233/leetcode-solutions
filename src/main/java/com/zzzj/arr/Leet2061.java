package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;


/**
 * @author zzzj
 * @create 2023-01-03 11:48
 */
public class Leet2061 {

    // 给你一只扫地机器人，起初它位于 [0, 0] 位置，面朝右侧，0 为空地，1 为障碍物
    // 只有当它当前朝向遇到了障碍物或者墙壁时，才可以将朝向顺时针旋转 90 度，问你它最多能清理多少空地。

    public static void main(String[] args) {
//        System.out.println(numberOfCleanRooms(LeetUtils.convertInts("[[0,0,0],[1,1,0],[0,0,0]]")));
        System.out.println(numberOfCleanRooms(LeetUtils.convertInts("[[0,0,0,1],[0,1,0,1],[1,0,0,0]]")));
    }

    public static int numberOfCleanRooms(int[][] room) {
        int M = room.length;
        int N = room[0].length;

        boolean[][] visited = new boolean[M][N];

        visited[0][0] = true;

        return dfs(room, visited, 0, 0, DIR_RIGHT);
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;

    public static int dfs(int[][] room, boolean[][] visited, int x, int y, int dir) {
        if (needShutdown(room, visited, x, y)) {
            return 1;
        }

        switch (dir) {
            case DIR_UP:
                if (x - 1 < 0 || room[x - 1][y] == 1) { // 撞墙
                    return dfs(room, visited, x, y, DIR_RIGHT);
                } else if (visited[x - 1][y]) { // 没有路可走了
                    return 1;
                } else {
                    visited[x - 1][y] = true;
                    return 1 + dfs(room, visited, x - 1, y, dir);
                }
            case DIR_DOWN:
                if (x + 1 >= room.length || room[x + 1][y] == 1) {
                    return dfs(room, visited, x, y, DIR_LEFT);
                } else if (visited[x + 1][y]) {
                    return 1;
                } else {
                    visited[x + 1][y] = true;
                    return 1 + dfs(room, visited, x + 1, y, dir);
                }
            case DIR_LEFT:
                if (y - 1 < 0 || room[x][y - 1] == 1) {
                    return dfs(room, visited, x, y, DIR_UP);
                } else if (visited[x][y - 1]) {
                    return 1;
                } else {
                    visited[x][y - 1] = true;
                    return 1 + dfs(room, visited, x, y - 1, dir);
                }
            case DIR_RIGHT:
                if (y + 1 >= room[x].length || room[x][y + 1] == 1) {
                    return dfs(room, visited, x, y, DIR_DOWN);
                } else if (visited[x][y + 1]) {
                    return 1;
                } else {
                    visited[x][y + 1] = true;
                    return 1 + dfs(room, visited, x, y + 1, dir);
                }
        }

        return -1;
    }



    public static boolean needShutdown(int[][] room, boolean[][] visited, int x, int y) {
        // 上下左边全部visited , 或者撞墙
        for (int[] dir : DIRS) {
            int row = dir[0];
            int col = dir[1];

            if (x + row >= 0 && x + row < room.length && y + col >= 0 && y + col < room[0].length && !visited[x + row][y + col] && room[x + row][y + col] != 1) {
                return false;
            }

        }
        return true;
    }

}
