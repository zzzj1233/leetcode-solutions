package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2022-07-22 16:22
 */
public class Leet827 {

    public static void main(String[] args) {

        System.out.println(largestIsland(LeetUtils.convertInts("[[1, 0, 1], [0, 1, 0], [1, 0, 1]]")));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[][] matrix = LeetUtils.randomMatrix(50, 50, 0, 2);
            if (largestIsland(matrix) != right(matrix)) {
                System.out.println("Error");
                System.out.println(Arrays.deepToString(matrix));
                System.out.println(largestIsland(matrix));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int largestIsland(int[][] grid) {

        int N = grid.length;
        int M = grid[0].length;

        UnionFind uf = new UnionFind(N * M);

        boolean[][] visited = new boolean[N][M];

        int ans = 0;

        int oneCount = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                int index = index(i, j, M);

                if (grid[i][j] == 1) {
                    if (!visited[i][j]) {
                        connection(i, j, visited, index, uf, grid);
                    }
                    oneCount++;
                }
            }

        }

        Set<Integer> computed = new HashSet<>(8);

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                int index = index(i, j, M);

                if (grid[i][j] == 0) {
                    // 上下左右
                    int max = 1;

                    for (int[] dir : DIRS) {
                        int row = dir[0];
                        int col = dir[1];

                        if (i + row >= 0 && i + row < N && j + col >= 0 && j + col < M && grid[i + row][j + col] == 1) {

                            int index1 = index(i + row, j + col, M);

                            int parent = uf.getParent(index1);

                            if (computed.contains(parent)) {
                                continue;
                            }

                            computed.add(parent);

                            int size = uf.size[parent];

                            max += size;
                        }

                    }

                    ans = Math.max(ans, max);
                    computed.clear();
                }

            }

        }

        return ans == 0 ? oneCount : ans;
    }

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static void connection(int i, int j, boolean[][] visited, int index, UnionFind uf, int[][] grid) {
        if (visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        uf.connect(index(i, j, visited[i].length), index);

        for (int[] dir : DIRS) {
            int row = dir[0];
            int col = dir[1];
            if (i + row >= 0 && i + row < visited.length && j + col >= 0 && j + col < visited[i].length && !visited[i + row][j + col] && grid[i + row][j + col] == 1) {
                connection(i + row, j + col, visited, index, uf, grid);
            }
        }
    }

    public static int index(int i, int j, int M) {
        return (i * M) + j;
    }

    private static class UnionFind {

        int[] parent;
        int[] rank;
        int[] size;

        private UnionFind(int N) {
            parent = new int[N];
            rank = new int[N];
            size = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 1;
                size[i] = 1;
            }

        }

        private int getParent(int index) {
            while (index != parent[index]) {
                index = parent[parent[index]];
            }
            return index;
        }

        public void connect(int i, int j) {
            int parent1 = getParent(i);
            int parent2 = getParent(j);

            if (parent1 == parent2) {
                return;
            }

            int rank1 = rank[parent1];
            int rank2 = rank[parent2];

            if (rank1 == rank2) {
                parent[parent2] = parent1;
                rank[parent1]++;
                size[parent1] += size[parent2];
            } else if (rank1 > rank2) {
                parent[parent2] = parent1;
                size[parent1] += size[parent2];
            } else {
                parent[parent1] = parent2;
                size[parent2] += size[parent1];
            }
        }

    }

    /*********************** Copy form leetcode official solution ****************************/

    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, -1, 0, 1};

    public static int right(int[][] grid) {
        int N = grid.length;

        int ans = 0;
        boolean hasZero = false;
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 0) {
                    hasZero = true;
                    grid[r][c] = 1;
                    ans = Math.max(ans, check(grid, r, c));
                    grid[r][c] = 0;
                }

        return hasZero ? ans : N * N;
    }

    public static int check(int[][] grid, int r0, int c0) {
        int N = grid.length;
        Stack<Integer> stack = new Stack();
        Set<Integer> seen = new HashSet();
        stack.push(r0 * N + c0);
        seen.add(r0 * N + c0);

        while (!stack.isEmpty()) {
            int code = stack.pop();
            int r = code / N, c = code % N;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k], nc = c + dc[k];
                if (!seen.contains(nr * N + nc) && 0 <= nr && nr < N &&
                        0 <= nc && nc < N && grid[nr][nc] == 1) {
                    stack.push(nr * N + nc);
                    seen.add(nr * N + nc);
                }
            }
        }

        return seen.size();
    }


}
