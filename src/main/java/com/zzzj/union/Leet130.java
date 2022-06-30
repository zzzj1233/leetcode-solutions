package com.zzzj.union;


import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-11-13 19:53
 */
public class Leet130 {

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(board);

        System.out.println(Arrays.deepToString(board));

    }

    private static char[][] b;
    private static int sum;

    public static void solve(char[][] board) {
        b = board;
        if (board == null) {
            return;
        }

        int n = board.length;

        if (n == 0) {
            return;
        }

        int m = board[0].length;

        if (m == 0) {
            return;
        }

        sum = n * m + 1;

        UnionFind unionFind = new UnionFind(n * m + 2);

        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O') {
                addToUf(0, i, n, m, unionFind);
            }
            if (board[n - 1][i] == 'O') {
                addToUf(n - 1, i, n, m, unionFind);
            }
        }

        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                // 全部加入集合中
                addToUf(i, 0, n, m, unionFind);
            }
            if (board[i][m - 1] == 'O') {
                addToUf(i, m - 1, n, m, unionFind);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!unionFind.inSameSet(getIndex(i, j, m), sum)) {
                    board[i][j] = 'X';
                }
            }
        }

    }

    private static int getIndex(int i, int j, int m) {
        return i * m + j;
    }

    private static void addToUf(int i, int j, int n, int m, UnionFind unionFind) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return;
        }
        if (unionFind.inSameSet(getIndex(i, j, m), sum)) {
            return;
        }

        if (b[i][j] != 'O') {
            return;
        }

        unionFind.union(getIndex(i, j, m), sum);


        addToUf(i + 1, j, n, m, unionFind);
        addToUf(i, j + 1, n, m, unionFind);
        addToUf(i - 1, j, n, m, unionFind);
        addToUf(i, j - 1, n, m, unionFind);
    }


}
