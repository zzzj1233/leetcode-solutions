package com.zzzj.uf;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-12-30 17:40
 */
public class Leet1202 {

    public static void main(String[] args) {
        System.out.println(smallestStringWithSwaps("dcab", LeetUtils.convertLists("[[0,3],[1,2]]")));
        System.out.println(smallestStringWithSwaps("dcab", LeetUtils.convertLists("[[0,3],[1,2],[0,2]]")));

        System.out.println(smallestStringWithSwaps("cba", LeetUtils.convertLists("[[0,1],[1,2]]")));
    }

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

        int N = s.length();

        UF uf = new UF(N);

        for (List<Integer> pair : pairs) {
            Integer index0 = pair.get(0);
            Integer index1 = pair.get(1);

            uf.union(index0, index1);
        }

        // 对uf的每一块进行排序
        char[] chars = new char[N];

        // key = parent , value = area list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int parent = uf.getParent(i);
            graph.computeIfAbsent(parent, ignore -> new ArrayList<>())
                    .add(i);
        }

        for (List<Integer> list : graph.values()) {
            List<Integer> sorted = new ArrayList<>(list);

            sorted.sort(Comparator.comparingInt(s::charAt));

            for (int i = 0; i < list.size(); i++) {
                chars[list.get(i)] = s.charAt(sorted.get(i));
            }

        }

        return new String(chars);
    }

    private static class UF {
        final int[] parent;
        final int[] rank;

        public UF(int N) {
            this.parent = new int[N];
            this.rank = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }

        private int getParent(int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }
            return parent[index];
        }

        public boolean isConnected(int x, int y) {
            int px = getParent(x);
            int py = getParent(y);

            return px == py;
        }

        public void union(int x, int y) {
            int px = getParent(x);
            int py = getParent(y);

            if (px == py) {
                return;
            } else if (rank[px] == rank[py]) {
                parent[px] = py;
                rank[py]++;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else { // rank[px] < rank[py]
                parent[px] = py;
            }
        }
    }

}
