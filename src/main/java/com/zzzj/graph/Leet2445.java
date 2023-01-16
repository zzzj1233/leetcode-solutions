package com.zzzj.graph;

import java.util.HashSet;
import java.util.Set;

public class Leet2445 {

    public static void main(String[] args) {
        System.out.println(numberOfNodes(5, new int[]{1, 2, 5}));
    }

    public static int numberOfNodes(int n, int[] queries) {

        Set<Integer> items = new HashSet<>(queries.length);

        for (int query : queries) {
            if (!items.add(query)) {
                items.remove(query);
            }
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            boolean red = false;
            int cur = i;
            while (cur > 0) {
                if (items.contains(cur)) {
                    red = !red;
                }
                cur /= 2;
            }
            if (red) {
                ans++;
            }
        }

        return ans;
    }

}
