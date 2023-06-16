package com.zzzj.uf;

/**
 * @author zzzj
 * @create 2022-10-31 17:23
 */
public class UF {

    private final int n;
    int[] parents;
    int[] rank;

    public UF(int N) {
        parents = new int[N];
        rank = new int[N];
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
}
