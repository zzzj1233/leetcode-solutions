package com.zzzj.contest.week392;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-05-23 17:59
 */
public class Leet3108 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(minimumCost(5, LeetUtils.convertInts("[[0,1,7],[1,3,7],[1,2,1]]"), LeetUtils.convertInts("[[0,3],[3,4]]"))));

        System.out.println(Arrays.toString(minimumCost(3, LeetUtils.convertInts("[[0,2,7],[0,1,15],[1,2,6],[1,2,1]]"), LeetUtils.convertInts("[[1,2]]"))));

    }

    public static int[] minimumCost(int n, int[][] edges, int[][] query) {

        int M = query.length;

        int[] ans = new int[M];

        Arrays.fill(ans, -1);

        UF uf = new UF(n);

        for (int[] edge : edges)
            uf.connect(edge[0], edge[1], edge[2]);

        for (int i = 0; i < M; i++) {
            int x = query[i][0];
            int y = query[i][1];
            if (uf.isConnected(x, y))
                ans[i] = uf.v[uf.parent(x)];
        }

        return ans;
    }

    public static class UF {

        int[] p;
        int[] v;
        int[] r;

        public UF(int N) {
            this.p = new int[N];
            this.v = new int[N];
            this.r = new int[N];
            Arrays.fill(v, Integer.MAX_VALUE);
            for (int i = 0; i < N; i++)
                this.p[i] = i;
        }

        public void connect(int x, int y, int v) {
            int px = parent(x);
            int py = parent(y);

            if (px == py){
                this.v[px] &= v;
                return;
            }

            if (r[px] > r[py]) {
                p[py] = px;
                this.v[px] &= v;
                this.v[px] &= this.v[py];
            } else if (r[px] < r[py]) {
                p[px] = py;
                this.v[py] &= v;
                this.v[py] &= this.v[px];
            } else {
                p[py] = px;
                r[px]++;
                this.v[px] &= v;
                this.v[px] &= this.v[py];
            }
        }

        public boolean isConnected(int x, int y) {
            return parent(x) == parent(y);
        }

        public int parent(int i) {
            while (p[i] != i) {
                i = p[i] = p[p[i]];
            }
            return i;
        }
    }

}
