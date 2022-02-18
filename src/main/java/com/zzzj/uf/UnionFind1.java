package com.zzzj.uf;

/**
 * @author Zzzj
 * @create 2021-05-06 21:57
 */
public class UnionFind1 implements IUf {

    private int[] id;

    private int size;

    public UnionFind1(int size) {
        this.id = new int[size];
        this.size = size;

        // 一开始所有元素都不互通
        for (int i = 0; i < this.id.length; i++) {
            this.id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    // on
    @Override
    public void union(int i, int j) {
        int iId = id[i];
        int jId = id[j];

        if (iId == jId) {
            return;
        }

        // 把和j一边的全部转换为和i一边,保持互通性
        for (int k = 0; k < this.size; k++) {
            if (id[k] == jId) {
                id[k] = iId;
            }
        }
    }

    // o1
    @Override
    public boolean isConnected(int i, int j) {
        return id[i] == id[j];
    }

}
