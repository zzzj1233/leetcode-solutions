package com.zzzj.uf;

/**
 * @author zzzj
 * @create 2022-10-08 17:57
 */
public class Leet990 {


    public static boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);

        for (String equation : equations) {
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            boolean equal = equation.charAt(1) == '=';
            if (equal) {
                uf.connect(a - 'a', b - 'a');
            }
        }

        for (String equation : equations) {
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            boolean equal = equation.charAt(1) == '=';
            if (!equal) {
                if (uf.isConnected(a - 'a', b - 'a')) {
                    return false;
                }
            }
        }

        return true;
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
