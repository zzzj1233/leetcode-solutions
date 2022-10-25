package com.zzzj.leet;


public class Leet1905 {


    public static void main(String[] args) {
        int[][] grid1 = LeetUtils.convertInts("[[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]");
        int[][] grid2 = LeetUtils.convertInts("[[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]");

        System.out.println(countSubIslands(grid1, grid2));
    }

    private static class UF {
        int[] parent;

        public UF(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int parentX = getParent(x);
            int parentY = getParent(y);

            if (parentX == parentY) {
                return;
            }

            parent[parentX] = parentY;
        }

        private int getParent(int index) {
            while (index != parent[index]) {
                index = parent[parent[index]];
            }
            return index;
        }

        public boolean isConnected(int x, int y) {
            return getParent(x) == getParent(y);
        }

    }

    public static int countSubIslands(int[][] grid1, int[][] grid2) {

        int M = grid1.length;
        int N = grid1[0].length;

        UF uf1 = new UF(M * N);

        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid1[i][j] == 1 && !visited[i][j]) {
                    dfs(grid1, i, j, visited, uf1, index(i, j, N));
                }
            }
        }

        visited = new boolean[M][N];

        int ans = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid2[i][j] == 1 && !visited[i][j] && grid1[i][j] == 1) {
                    if (dfs2(grid2, i, j, visited, uf1, index(i, j, N))) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    public static boolean dfs2(int[][] grid, int i, int j, boolean[][] visited, UF uf, int index) {
        if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0) {
            return true;
        }

        if (grid[i][j] != 1) {
            return true;
        }

        if (visited[i][j]) {
            return true;
        }

        visited[i][j] = true;

        int curIndex = index(i, j, grid[i].length);

        boolean result = true;

        if (!uf.isConnected(curIndex, index)) {
            result = false;
        }

        result = result & dfs2(grid, i + 1, j, visited, uf, index);
        result = result & dfs2(grid, i - 1, j, visited, uf, index);
        result = result & dfs2(grid, i, j + 1, visited, uf, index);
        result = result & dfs2(grid, i, j - 1, visited, uf, index);

        return result;
    }

    public static void dfs(int[][] grid, int i, int j, boolean[][] visited, UF uf, int preIndex) {
        if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        if (grid[i][j] != 1) {
            return;
        }

        int index = index(i, j, grid[i].length);

        uf.union(index, preIndex);

        visited[i][j] = true;

        dfs(grid, i + 1, j, visited, uf, index);
        dfs(grid, i - 1, j, visited, uf, index);
        dfs(grid, i, j + 1, visited, uf, index);
        dfs(grid, i, j - 1, visited, uf, index);
    }

    public static int index(int row, int col, int N) {
        return row * N + col;
    }

}
