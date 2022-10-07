package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-10-07 22:17
 */
public class Leet1319 {


    public static void main(String[] args) {
        System.out.println(makeConnected(6, LeetUtils.convertInts("[[0,1],[0,2],[0,3],[1,2],[1,3]]")));
    }

    public static int makeConnected(int n, int[][] connections) {
        UF uf = new UF(n);

        int canUse = 0;

        for (int[] connection : connections) {
            int x = connection[0];
            int y = connection[1];
            if (uf.isConnected(x, y)) {
                canUse++;
            } else {
                uf.connect(x, y);
            }
        }

        return uf.size - 1 <= canUse ? uf.size - 1 : -1;
    }

    private static class UF {
        final int[] parents;

        int size;

        public UF(int N) {
            parents = new int[N];

            size = N;

            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
        }

        public void connect(int x, int y) {
            int xp = getParent(x);
            int yp = getParent(y);

            if (xp == yp) {
                return;
            }
            parents[xp] = yp;
            size--;
        }

        public boolean isConnected(int x, int y) {
            return getParent(x) == getParent(y);
        }

        private int getParent(int it) {
            while (parents[it] != it) {
                parents[it] = parents[parents[it]];
                it = parents[it];
            }
            return it;
        }


    }

}
