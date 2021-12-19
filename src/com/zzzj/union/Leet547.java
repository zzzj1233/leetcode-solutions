package com.zzzj.union;

/**
 * @author Zzzj
 * @create 2021-11-14 14:29
 */
public class Leet547 {


    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }

        }

        return unionFind.getSets();
    }

}
