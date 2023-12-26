package com.zzzj.greedy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-12-22 15:10
 */
public class Leet2948 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(lexicographicallySmallestArray(
                new int[]{1, 5, 3, 9, 8},
                2
        )));

        System.out.println(Arrays.toString(lexicographicallySmallestArray(
                new int[]{1, 7, 6, 18, 2, 1},
                3
        )));

    }

    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int N = nums.length;

        UF uf = new UF(N);

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            Integer p = map.get(nums[i]);
            if (p != null)
                uf.union(p, i);
            else
                map.put(nums[i], i);
        }

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(num - limit);

            if (ceiling != null && ceiling.getKey() != num) {
                uf.union(map.get(num), ceiling.getValue());
                continue;
            }

            Map.Entry<Integer, Integer> floor = map.floorEntry(num + limit);

            if (floor != null && floor.getKey() != null)
                uf.union(map.get(num), floor.getValue());

        }

        Map<Integer, List<Integer>> lists = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int p = uf.getParent(i);
            lists.computeIfAbsent(p, k -> new ArrayList(uf.size[p])).add(nums[i]);
        }

        for (List<Integer> value : lists.values())
            Collections.sort(value);

        Map<Integer, Integer> indexes = lists.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, ignore -> 0));

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {

            int parent = uf.getParent(i);

            List<Integer> list = lists.get(parent);

            Integer index = indexes.get(parent);

            ans[i] = list.get(index);

            indexes.put(parent, index + 1);
        }

        return ans;
    }

    private static class UF {

        private final int n;
        int[] parents;
        int[] rank;
        int[] size;

        public UF(int N) {
            parents = new int[N];
            rank = new int[N];
            size = new int[N];
            n = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
        }

        public int getSize() {
            return n;
        }

        public void union(int x, int y) {
            int parentX = getParent(x);
            int parentY = getParent(y);

            if (parentX == parentY) {
                return;
            }

            if (rank[parentX] > rank[parentY]) {
                parents[parentY] = parentX;
                size[parentX] += size[parentY];
                size[parentY] = 0;
            } else if (rank[parentX] < rank[parentY]) {
                parents[parentX] = parentY;
                size[parentY] += size[parentX];
                size[parentX] = 0;
            } else {  // 层级一样,挂载x上
                parents[parentY] = parentX;
                rank[parentX]++;
                size[parentX] += size[parentY];
                size[parentY] = 0;
            }
        }

        public boolean isConnected(int x, int y) {
            return getParent(x) == getParent(y);
        }

        private int getParent(int index) {
            while (parents[index] != index) {
                // 路径压缩
                parents[index] = parents[parents[index]];
                index = parents[index];
            }
            return index;
        }
    }
}
