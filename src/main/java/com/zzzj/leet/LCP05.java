package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-01-02 16:40
 */
public class LCP05 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(bonus(
                6,
                LeetUtils.convertInts("[[1, 2], [1, 6], [2, 3], [2, 5], [1, 4]]"),
                LeetUtils.convertInts("[[1, 1, 500], [2, 2, 50], [3, 1], [2, 6, 15], [3, 1]]")
        )));

    }

    public static int[] bonus(int n, int[][] leadership, int[][] operations) {

        List<Integer> ans = new ArrayList<>();

        Map<Integer, Set<Integer>> r = new HashMap<>();

        for (int[] item : leadership)
            r.computeIfAbsent(item[0], integer -> new HashSet<>()).add(item[1]);

        int[][] range = new int[n + 1][2];

        dfs(r, 1, range, 0);

        SegmentTree tree = new SegmentTree(n + 1);

        for (int[] operation : operations) {

            if (operation[0] == 1) {
                tree.update(range[operation[1]][0], range[operation[1]][0], operation[2]);
            } else if (operation[0] == 2) {
                tree.update(range[operation[1]][0], range[operation[1]][1], operation[2]);
            } else {
                ans.add((int) (tree.query(range[operation[1]][0], range[operation[1]][1]) % 1000000007));
            }

        }

        return ans.stream().mapToInt(value -> value).toArray();
    }

    private static class SegmentTree {

        private final int N;

        private final long[] tree;

        private final long[] lazy;

        private SegmentTree(int n) {
            this.N = n + 1;
            this.tree = new long[this.N << 2];
            this.lazy = new long[this.N << 2];
        }

        public long query(int L, int R) {
            return query(L + 1, R + 1, 1, N, 1);
        }

        private long query(int L, int R, int l, int r, int rt) {

            if (L <= l && R >= r) {
                return tree[rt];
            }

            pushDown(l, r, rt);

            int mid = l + ((r - l) >> 1);

            long sum = 0;

            if (L <= mid)
                sum = query(L, R, l, mid, leftChild(rt));
            if (R > mid)
                sum += query(L, R, mid + 1, r, rightChild(rt));

            return sum;
        }

        public void update(int L, int R, long V) {
            update(L + 1, R + 1, V, 1, N, 1);
        }

        private void update(int L, int R, long V, int l, int r, int rt) {

            if (l == r) {
                tree[rt] += V;
                return;
            }

            int mid = l + ((r - l) >> 1);

            int cnt = r - l + 1;

            if (L <= l && R >= r) {
                tree[rt] += cnt * V;
                lazy[rt] += V;
                return;
            }

            pushDown(l, r, rt);

            if (L <= mid)
                update(L, R, V, l, mid, leftChild(rt));
            if (R > mid)
                update(L, R, V, mid + 1, r, rightChild(rt));

            pushUp(rt);
        }

        private int leftChild(int rt) {
            return rt << 1;
        }

        private int rightChild(int rt) {
            return rt << 1 | 1;
        }

        private void pushDown(int l, int r, int rt) {
            if (lazy[rt] == 0)
                return;

            int mid = l + ((r - l) >> 1);

            int leftCnt = mid - l + 1;

            int rightCnt = r - mid;

            long V = lazy[rt];

            lazy[leftChild(rt)] += V;
            lazy[rightChild(rt)] += V;

            tree[leftChild(rt)] += V * leftCnt;
            tree[rightChild(rt)] += V * rightCnt;

            lazy[rt] = 0;
        }

        private void pushUp(int rt) {
            tree[rt] = tree[leftChild(rt)] + tree[rightChild(rt)];
        }

    }

    private static int dfs(
            Map<Integer, Set<Integer>> r,
            int node,
            int[][] range,
            int index
    ) {
        range[node][0] = index;

        Set<Integer> children = r.getOrDefault(node, Collections.emptySet());

        for (Integer child : children)
            index = dfs(r, child, range, index + 1);

        range[node][1] = index;

        return index;
    }

}
