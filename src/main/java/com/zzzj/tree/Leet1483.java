package com.zzzj.tree;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-08 14:12
 */
public class Leet1483 {

    public static void main(String[] args) {

        TreeAncestor treeAncestor = new TreeAncestor(
                6, new int[]{-1, 2, 3, 4, 5, 0}
        );

        // System.out.println(Arrays.toString(treeAncestor.pa[1]));

        // System.out.println("treeAncestor.pa[2] = " + Arrays.toString(treeAncestor.pa[2]));

    }


    private static class TreeAncestor {

        private final int[][] pa;

        public TreeAncestor(int n, int[] parent) {

            pa = new int[n][32];

            for (int i = 0; i < n; i++) Arrays.fill(pa[i], -1);

            for (int i = 0; i < n; i++)
                pa[i][0] = parent[i];

            for (int bit = 1; bit < 31; bit++) {

                for (int node = 0; node < n; node++) {

                    int p = pa[node][bit - 1];

                    if (p == -1)
                        continue;

                    pa[node][bit] = pa[p][bit - 1];
                }

            }

//            for (int i = 0; i < n; i++) {
//
//                int p = parent[i];
//
//                for (int j = 1; j < 32 && p != -1; j++) {
//                    p = pa[p][j - 1];
//                    pa[i][j] = p;
//                }
//
//            }

        }

        public int getKthAncestor(int node, int k) {

            for (int i = 0; i <= 31 && node != -1; i++) {
                if ((k & (1 << i)) != 0) {
                    node = pa[node][i];
                }
            }

            return node;
        }

    }

}
