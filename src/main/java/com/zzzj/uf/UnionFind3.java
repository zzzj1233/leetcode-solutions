package com.zzzj.uf;

/**
 * @author Zzzj
 * @create 2021-05-06 21:57
 */
public class UnionFind3 implements IUf {

    private int[] parent;

    // 记录每个元素的下面挂着的元素个数,用于形成一个更加平衡的二叉树
    private int[] sz;

    private int size;

    public UnionFind3(int size) {
        this.parent = new int[size];
        this.sz = new int[size];
        this.size = size;

        // 一开始所有元素都不互通
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;

            // 一开始所有元素都只挂有一个元素
            this.sz[i] = 1;
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

        // 小的元素挂到大的元素下面
        if (sz[iParent] < sz[jParent]) {
            parent[iParent] = jParent;
            sz[jParent] += sz[iParent];
        } else {
            parent[jParent] = iParent;
            sz[iParent] += sz[jParent];
        }
    }

    private int findParent(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    @Override
    public boolean isConnected(int i, int j) {
        return findParent(i) == findParent(j);
    }

}
