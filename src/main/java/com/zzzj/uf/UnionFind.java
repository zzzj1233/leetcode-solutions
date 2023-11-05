package com.zzzj.uf;

/**
 * @author Zzzj
 * @create 2021-04-24 23:00
 */
public class UnionFind {

    private int[] parent;

    private int[] height;

    private int sets;

    public UnionFind(int size) {
        parent = new int[size];
        height = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        sets = size;
    }

    public UnionFind(int size, int dummy) {
        parent = new int[size];
        height = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = dummy;
        }
        height[dummy] = size;
    }

    public boolean inSameSet(int p, int q) {
        return findParent(p) == findParent(q);
    }

    public void union(int p, int q) {
        int pParent = findParent(p);
        int qParent = findParent(q);

        if (pParent == qParent) {
            return;
        }

        sets--;

        int pHeight = height[p];
        int qHeight = height[q];

        if (pHeight > qHeight) {
            parent[qParent] = pParent;
        } else if (qHeight > pHeight) {
            parent[pParent] = qParent;
        } else {
            parent[qParent] = pParent;
            height[qHeight] += 1;
        }
    }

    private int findParent(int p) {
        while (parent[p] != p) {
            p = parent[parent[p]];
        }
        return p;
    }

    public int getSets() {
        return sets;
    }

}
