package com.zzzj.bfs;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-10-06 19:22
 */
public class Leet864 {

    public static final int[][] DIRS = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };

    public static void main(String[] args) {

        System.out.println(shortestPathAllKeys(LeetUtils.convertString1("[\"@.a.#\",\"###.#\",\"b.A.B\"]")));

        System.out.println(shortestPathAllKeys(LeetUtils.convertString1("[\"@..aA\",\"..B#.\",\"....b\"]")));

        System.out.println(shortestPathAllKeys(LeetUtils.convertString1("[\"@Aa\"]")));

    }

    public static int shortestPathAllKeys(String[] grid) {

        LinkedList<int[]> queue = new LinkedList<>();

        int M = grid.length;
        int N = grid[0].length();

        boolean[][] visited = new boolean[M][N];

        int keys = 0;

        int step = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                char c = grid[i].charAt(j);

                if (c >= 'a' && c <= 'f') {
                    keys++;
                } else if (c == '@') {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] locked = new int[6][];

        boolean[] obtainKeys = new boolean[6];

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] pos = queue.removeFirst();

                int x = pos[0];

                int y = pos[1];

                char c = grid[x].charAt(y);

                // 锁
                if (c >= 'A' && c <= 'F') {
                    // 是否已经有钥匙了
                    int lockIndex = c - 'A';

                    if (!obtainKeys[lockIndex]) {
                        locked[lockIndex] = new int[]{x, y};
                        continue;
                    }
                } else if (c >= 'a' && c <= 'f') {
                    int lockIndex = c - 'a';

                    if (--keys == 0)
                        return step;

                    obtainKeys[lockIndex] = true;

                    if (locked[lockIndex] != null)
                        addToQueue(visited, queue, locked[lockIndex], grid);
                }

                addToQueue(visited, queue, pos, grid);
            }

            step++;
        }

        return -1;
    }

    public static void addToQueue(boolean[][] visited, LinkedList<int[]> queue, int[] pos, String[] grid) {
        int x = pos[0];

        int y = pos[1];

        for (int[] dir : DIRS) {
            int row = dir[0] + x;
            int col = dir[1] + y;
            if (row < 0 || col < 0 || row >= visited.length || col >= visited[row].length || visited[row][col] || grid[row].charAt(col) == '#')
                continue;

            queue.addLast(new int[]{row, col});
            visited[row][col] = true;
        }
    }

}
