package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet1619 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(pondSizes(LeetUtils.convertInts("[\n" +
                "  [0,2,1,0],\n" +
                "  [0,1,0,1],\n" +
                "  [1,1,0,1],\n" +
                "  [0,1,0,1]\n" +
                "]"))));
    }

    public static int[] pondSizes(int[][] land) {

        int M = land.length;

        int N = land[0].length;

        boolean[][] visited = new boolean[M][N];

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < M; i++) {

            for (int j = 0; j < N; j++) {

                if (land[i][j] == 0 && !visited[i][j]) {
                    list.add(dfs(land, i, j, visited));
                }

            }

        }

        return list.stream().sorted().mapToInt(value -> value).toArray();
    }

    public static final int[][] DIRS2 = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public static int dfs(int[][] land, int x, int y, boolean[][] visited) {

        if (visited[x][y] || land[x][y] != 0) return 0;

        visited[x][y] = true;

        int cnt = 1;

        for (int[] dir : DIRS2) {

            int r = dir[0] + x;

            int c = dir[1] + y;

            if (r >= 0 && c >= 0 && r < visited.length && c < visited[r].length && !visited[r][c]) {
                cnt += dfs(land, r, c, visited);
            }
        }

        return cnt;
    }

}
