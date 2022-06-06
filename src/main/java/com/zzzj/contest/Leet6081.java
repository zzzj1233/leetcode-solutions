package com.zzzj.contest;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.Unresolved;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-05-29 15:47
 */
@Unresolved
public class Leet6081 {

    public static void main(String[] args) {
        System.out.println(minimumObstacles(LeetUtils.convertInts("[[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]")));
    }

    private static class Node {
        int i;
        int j;
        int obstacles;

        public Node(int i, int j, int obstacles) {
            this.i = i;
            this.j = j;
            this.obstacles = obstacles;
        }
    }

    public static int minimumObstacles(int[][] grid) {

        LinkedList<Node> queue = new LinkedList<>();

        int[][] visited = new int[grid.length][grid[0].length];

        queue.add(new Node(0, 0, grid[0][0] & 1));

        int ans = Integer.MAX_VALUE;

        int N = grid.length;
        int M = grid[0].length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        int endObstacles = grid[N - 1][M - 1] & 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Node first = queue.removeFirst();

                int i = first.i;
                int j = first.j;

                int obstacles = first.obstacles;

                if (i == N - 1 && j == M - 1) {
                    ans = Math.min(ans, obstacles + endObstacles);
                    continue;
                }

                visited[i][j] = obstacles;

                // 上下左右
                if (i + 1 < N && obstacles < visited[i + 1][j]) {
                    queue.add(new Node(i + 1, j, obstacles + (grid[i + 1][j] & 1)));
                }

                if (i - 1 >= 0 && obstacles < visited[i - 1][j]) {
                    queue.add(new Node(i - 1, j, obstacles + (grid[i - 1][j] & 1)));
                }

                if (j + 1 < M && obstacles < visited[i][j + 1]) {
                    queue.add(new Node(i, j + 1, obstacles + (grid[i][j + 1] & 1)));
                }

                if (j - 1 >= 0 && obstacles < visited[i][j - 1]) {
                    queue.add(new Node(i, j - 1, obstacles + (grid[i][j - 1] & 1)));
                }

            }
        }

        return ans;
    }

}
