package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-10-31 17:14
 */
public class Leet305 {

    public static void main(String[] args) {
        System.out.println(numIslands2(3, 3, LeetUtils.convertInts("[[0,0],[0,1],[1,2],[2,1],[1,0],[0,0],[2,2],[1,2],[1,1],[0,1]]")));
    }

    private static class UF {

        private final int n;
        int[] parents;
        int[] rank;
        boolean[] isLand;

        public UF(int N) {
            parents = new int[N];
            rank = new int[N];
            isLand = new boolean[N];
            n = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
        }

        public int getSize() {
            return n;
        }

        public void union(int x, int y) {
            int parentX = getParent(x);
            int parentY = getParent(y);

            if (parentX == parentY) {
                return;
            }

            if (rank[parentX] > rank[parentY]) {
                parents[parentY] = parentX;
            } else if (rank[parentX] < rank[parentY]) {
                parents[parentX] = parentY;
            } else {  // 层级一样,挂载x上
                parents[parentY] = parentX;
                rank[parentX]++;
            }
        }

        public boolean isConnected(int x, int y) {
            return getParent(x) == getParent(y);
        }

        private int getParent(int index) {
            while (parents[index] != index) {
                // 路径压缩
                parents[index] = parents[parents[index]];
                index = parents[index];
            }
            return index;
        }

        public String toString(int m, int n) {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < n; j++) {
                    int index = i * n + j;

                    if (isLand[index]) {
                        builder.append(getParent(index));
                    } else {
                        builder.append('x');
                    }

                }

                builder.append("\n");
            }

            return builder.toString();
        }
    }


    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] DIRS = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        UF uf = new UF(m * n);

        List<Integer> ans = new ArrayList<>();

        int land = 0;

        Set<Integer> parents = new HashSet<>(4);

        for (int i = 0; i < positions.length; i++) {
            int row = positions[i][0];

            int col = positions[i][1];

            int index = row * n + col;

            if (uf.isLand[index]) {
                ans.add(land);
                continue;
            }

            for (int[] dir : DIRS) {
                int r = dir[0];
                int c = dir[1];

                if (isLand(row + r, col + c, uf, m, n)) {
                    int landIndex = (row + r) * n + (col + c);

                    uf.isLand[index] = true;

                    parents.add(uf.getParent(landIndex));
                }
            }

            if (parents.isEmpty()) {
                land++;
                uf.isLand[index] = true;
            } else {
                land -= parents.size() - 1;

                for (Integer it : parents) {
                    uf.union(it, index);
                }
            }

            parents.clear();

            ans.add(land);
        }


        return ans;
    }

    public static boolean isLand(int x, int y, UF uf, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return false;
        }
        return uf.isLand[x * n + y];
    }

}
