package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-10-31 17:53
 */
public class Leet2267 {

    public static void main(String[] args) {

        System.out.println(hasValidPath(LeetUtils.convertChars("[[\"(\",\"(\",\"(\"],[\")\",\"(\",\")\"],[\"(\",\"(\",\")\"],[\"(\",\"(\",\")\"]]")));

    }

    public static boolean hasValidPath(char[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        if (grid[0][0] == ')')
            return false;

        boolean[][][] visited = new boolean[M][N][M + N];

        LinkedList<Node> queue = new LinkedList<>();

        queue.add(new Node(0, 0, 1));

        while (!queue.isEmpty()) {

            Node node = queue.removeFirst();

            if (node.x == M - 1 && node.y == N - 1 && node.cnt == 0)
                return true;

            if (node.x + 1 < M) {

                char c = grid[node.x + 1][node.y];

                if (c == '(') {
                    if (!visited[node.x + 1][node.y][node.cnt + 1]) {
                        queue.addLast(new Node(node.x + 1, node.y, node.cnt + 1));
                        visited[node.x + 1][node.y][node.cnt + 1] = true;
                    }
                } else {
                    if (node.cnt > 0 && !visited[node.x + 1][node.y][node.cnt - 1]) {
                        queue.addLast(new Node(node.x + 1, node.y, node.cnt - 1));
                        visited[node.x + 1][node.y][node.cnt - 1] = true;
                    }
                }

            }

            if (node.y + 1 < N) {

                char c = grid[node.x][node.y + 1];

                if (c == '(') {
                    if (!visited[node.x][node.y + 1][node.cnt + 1]) {
                        queue.addLast(new Node(node.x, node.y + 1, node.cnt + 1));
                        visited[node.x][node.y + 1][node.cnt + 1] = true;
                    }
                } else {
                    if (node.cnt > 0 && !visited[node.x][node.y + 1][node.cnt - 1]) {
                        queue.addLast(new Node(node.x, node.y + 1, node.cnt - 1));
                        visited[node.x][node.y + 1][node.cnt - 1] = true;
                    }
                }
            }
        }

        return false;
    }

    private static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}
