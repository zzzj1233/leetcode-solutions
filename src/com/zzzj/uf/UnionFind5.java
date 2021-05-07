package com.zzzj.uf;

/**
 * @author Zzzj
 * @create 2021-05-06 21:57
 */

// 路径压缩
public class UnionFind5 implements IUf {

    private int[] parent;

    // 基于rank,rank比size更适合用于判读树的深度
    private int[] rank;

    private int size;

    public UnionFind5(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        this.size = size;

        // 一开始所有元素都不互通
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;

            // 一开始所有元素的rank都是1
            this.rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void union(int i, int j) {
        int iParent = findParent(i);
        int jParent = findParent(j);

        if (iParent == jParent) {
            return;
        }

        if (rank[iParent] < rank[jParent]) {
            parent[iParent] = jParent;
            // rank不变
        } else if (rank[iParent] > rank[jParent]) {
            parent[jParent] = iParent;
        } else {
            parent[jParent] = iParent;
            rank[iParent] += 1;
            // rank + 1 , 因为是挂在第一个元素下面
        }
    }

    private int findParent(int x) {
        while (x != parent[x]) {
            // old
            // x = parent[x];
            // 压缩路径
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    @Override
    public boolean isConnected(int i, int j) {
        return findParent(i) == findParent(j);
    }

}
