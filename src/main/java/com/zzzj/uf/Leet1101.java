package com.zzzj.uf;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Zzzj
 * @create 2022-09-25 16:51
 */
public class Leet1101 {

    private static class UF {

        private int size;

        int[] parent;

        public UF(int N) {
            parent = new int[N];
            this.size = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int parentX = getParent(x);
            int parentY = getParent(y);

            if (parentX == parentY) {
                return;
            }

            parent[parentX] = parentY;
            size--;
        }

        private int getParent(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

    }

    public static int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, Comparator.comparingInt(o -> o[0]));

        UF uf = new UF(n);

        for (int[] log : logs) {
            int x = log[1];
            int y = log[2];

            uf.union(x, y);
            if (uf.size == 1) {
                return log[0];
            }
        }

        return -1;
    }


}
