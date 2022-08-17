package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-17 22:05
 */
public class Leet427 {


    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
    }


    public static Node construct(int[][] grid) {
        return construct(grid, 0, 0, grid.length, grid[0].length);
    }

    public static Node construct(int[][] grid, int i, int j, int N, int M) {

        Node root = new Node(grid[i][j] == 1, true);

        for (int x = i; x < N; x++) {
            for (int y = j; y < M; y++) {
                // 当前矩阵不是值全一样的
                if (grid[x][y] != grid[i][j]) {

                    root.val = true;
                    root.isLeaf = false;

                    root.topLeft = construct(grid, i, j, (i + N) / 2, (j + M) / 2);
                    root.topRight = construct(grid, i, (j + M) / 2, (i + N) / 2, M);
                    root.bottomLeft = construct(grid, (i + N) / 2, j, N, (j + M) / 2);
                    root.bottomRight = construct(grid, (i + N) / 2, (j + M) / 2, N, M);

                    return root;
                }
            }
        }

        return root;
    }


}
