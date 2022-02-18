package com.zzzj.uf;

/**
 * @author Zzzj
 * @create 2021-05-06 21:57
 */
public class UnionFind2 implements IUf {

    private int[] parent;

    private int size;

    public UnionFind2(int size) {
        this.parent = new int[size];
        this.size = size;

        // 一开始所有元素都不互通
        for (int i = 0; i < this.parent.length; i++) {
            this.parent[i] = i;
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

        parent[jParent] = iParent;
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
