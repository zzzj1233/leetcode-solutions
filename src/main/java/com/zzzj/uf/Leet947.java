package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2023-06-16 16:35
 */
public class Leet947 {

    public static void main(String[] args) {
        System.out.println(removeStones(
                LeetUtils.convertInts("[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]")
        ));

        System.out.println(removeStones(
                LeetUtils.convertInts("[[0,0],[0,2],[1,1],[2,0],[2,2]]")
        ));

        System.out.println(removeStones(
                LeetUtils.convertInts("[[0,0]]")
        ));
    }

    public static int removeStones(int[][] stones) {
        int N = stones.length;

        UF uf = new UF(N);

        for (int i = 0; i < N; i++) for (int j = i + 1; j < N; j++) if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) uf.union(i, j);

        return N - uf.getSize();
    }

    private static class UF {

        private final int n;
        int[] parents;
        int[] rank;
        int size;

        public UF(int N) {
            parents = new int[N];
            rank = new int[N];
            n = N;
            size = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
        }

        public int getSize() {
            return size;
        }

        public void union(int x, int y) {
            int parentX = getParent(x);
            int parentY = getParent(y);

            if (parentX == parentY) {
                return;
            }
            size--;
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
    }
}
