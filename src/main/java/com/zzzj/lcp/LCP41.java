package com.zzzj.lcp;

import com.zzzj.leet.LeetUtils;

import java.util.LinkedList;

public class LCP41 {

    public static void main(String[] args) {

        System.out.println(flipChess(LeetUtils.convertString1("[\".......\",\".......\",\".......\",\"X......\",\".O.....\",\"..O....\",\"....OOX\"]")));

        System.out.println(flipChess(LeetUtils.convertString1("[\"....X.\",\"....X.\",\"XOOO..\",\"......\",\"......\"]")));

        System.out.println(flipChess(LeetUtils.convertString1("[\".O.....\",\".O.....\",\"XOO.OOX\",\".OO.OO.\",\".XO.OX.\",\"..X.X..\"]")));

        System.out.println(flipChess(LeetUtils.convertString1("[\".......\",\"X......\",\".O.....\",\"..O...X\",\"...OOOX\",\"....O..\",\"...OOOX\"]")));
    }

    public static int flipChess(String[] chessboard) {

        int M = chessboard.length;

        int N = chessboard[0].length();

        char[][] tab = new char[M][];

        for (int i = 0; i < M; i++) {
            tab[i] = chessboard[i].toCharArray();
        }

        int ans = 0;

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                // 可以下棋
                if (tab[i][j] == '.') {
                    ans = Math.max(ans, check(i, j, tab, new boolean[M][N]));
                }

            }

        }

        return ans;
    }

    public static final int[][] DIRS2 = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static int check(int i, int j, char[][] tab, boolean[][] visited) {

        int cnt = 0;

        LinkedList<int[]> queue = new LinkedList<>();

        for (int[] dir : DIRS2) {

            int r = dir[0] + i;

            int c = dir[1] + j;

            boolean find = false;

            while (r >= 0 && c >= 0 && r < tab.length && c < tab[r].length && !visited[r][c]) {

                if (tab[r][c] == 'X') {
                    find = true;
                    break;
                } else if (tab[r][c] == 'O') {
                    queue.add(new int[]{r, c});
                } else {
                    break;
                }

                r += dir[0];
                c += dir[1];
            }

            if (find) {

                cnt += queue.size();

                for (int[] ints : queue) {
                    visited[ints[0]][ints[1]] = true;
                }

                while (!queue.isEmpty()) {

                    int[] rm = queue.removeLast();

                    cnt += check(rm[0], rm[1], tab, visited);
                }

            } else queue.clear();

        }

        return cnt;
    }
}
