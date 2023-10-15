package com.zzzj.dp;

import java.util.Arrays;

public class Leet2407 {

    public static void main(String[] args) {

        System.out.println(lengthOfLIS(new int[]{4, 2, 1, 4, 3, 4, 5, 8, 100000}, 3));

        System.out.println(lengthOfLIS(new int[]{7, 4, 5, 1, 8, 12, 4, 7}, 5));

        System.out.println(lengthOfLIS(new int[]{1, 5}, 1));

    }

    public static int lengthOfLIS(int[] nums, int k) {

        int N = nums.length;

        int[] dp = new int[N];

        int ans = 0;

        int max = Arrays.stream(nums).max().orElse(0);

        SegmentTree tree = new SegmentTree(max);

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            dp[i] = 1;

            dp[i] = Math.max(dp[i], tree.query(num - k, num - 1) + 1);

            tree.update(num, dp[i]);

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static class SegmentTree {

        public final int[] tree;

        public final int N;

        public SegmentTree(int n) {
            this.N = n + 1;
            this.tree = new int[N << 2];
        }

        public void update(int index, int value) {
            update(index + 1, value, 1, N, 1);
        }

        private void update(int index, int value, int l, int r, int rt) {
            if (l == index && r == index) {
                tree[rt] = Math.max(tree[rt], value);
                return;
            }

            int mid = l + ((r - l) >> 1);

            if (index <= mid)
                update(index, value, l, mid, rt << 1);
            else
                update(index, value, mid + 1, r, rt << 1 | 1);

            tree[rt] = Math.max(tree[rt << 1], tree[rt << 1 | 1]);
        }

        public int query(int L, int R) {
            return query(L + 1, R + 1, 1, N, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {
            if (l >= L && r <= R)
                return tree[rt];

            int mid = l + ((r - l) >> 1);

            int v = 0;
            if (L <= mid)
                v = query(L, R, l, mid, rt << 1);
            if (R > mid)
                v = Math.max(v, query(L, R, mid + 1, r, rt << 1 | 1));

            return v;
        }

    }

}
