package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-06-26 21:20
 */
public class Leet130 {

    private static class UF {
        int[] rank;

        public UF(int size) {
            this.rank = new int[size];
        }

        public void union(int p, int q) {
            int pParent = find(p);

            int qParent = find(q);

            if (pParent == qParent) {
                return;
            }

            if (rank[pParent] == rank[qParent]) {
                rank[pParent] = qParent;

            }

        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public int find(int p) {
            while (p != rank[p]) {
                p = rank[rank[p]];
            }
            return p;
        }
    }

    public static void solveUf(char[][] board) {

    }

    public static void solve(char[][] board) {
        // 从上下左右的0出发

        int N = board.length;
        int M = board[0].length;

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < M; i++) {
            if (board[0][i] == 'O') {
                dfs(0, i, visited, board);
            }
            if (board[N - 1][i] == 'O') {
                dfs(N - 1, i, visited, board);
            }
        }

        for (int i = 0; i < N; i++) {
            if (board[i][0] == 'O') {
                dfs(i, 0, visited, board);
            }
            if (board[i][M - 1] == 'O') {
                dfs(i, M - 1, visited, board);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }

    }

    public static int[][] DIRS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
    };

    public static void dfs(int i, int j, boolean[][] visited, char[][] board) {
        visited[i][j] = true;
        board[i][j] = '1';

        for (int[] dir : DIRS) {
            int row = dir[0];
            int col = dir[1];

            if (i + row >= 0 && i + row < board.length && j + col >= 0 && j + col < board[i].length && board[i + row][j + col] == '0' && !visited[i + row][j + col]) {
                dfs(i + row, j + col, visited, board);
            }
        }

    }

}
