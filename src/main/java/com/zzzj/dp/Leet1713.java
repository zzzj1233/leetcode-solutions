package com.zzzj.dp;


import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-10-08 12:01
 */
public class Leet1713 {

    public static void main(String[] args) {

        System.out.println(minOperations(
                new int[]{6, 3, 1, 5, 4},
                new int[]{5, 3, 3, 1, 4, 6}
        ));

//        System.exit(0);

        for (int i = 0; i < 1000; i++) {

            int M = 1000;

            int[] distinct = ArrayUtil.distinct(ArrayUtil.generateArray(M, 1, M));

            ArrayCopyIterator target = new ArrayCopyIterator(distinct);
            ArrayCopyIterator arr = new ArrayCopyIterator(ArrayUtil.generateArray(M, 1, M));

            int r1 = minOperations(target.next(), arr.next());

            int r2 = right(target.next(), arr.next());

            if (r1 != r2) {
                System.out.println("Error");
                System.out.println(Arrays.toString(target.next()));
                System.out.println(Arrays.toString(arr.next()));
                System.out.println("r1 = " + r1);
                System.out.println("r2 = " + r2);
                return;
            }

        }

        System.out.println("OK~");
    }

    public static int minOperations(int[] target, int[] arr) {

        int N = target.length;

        Map<Integer, Integer> indexes = new HashMap<>(N);

        for (int i = 0; i < target.length; i++)
            indexes.put(target[i], i);


        BinaryIndexedTree tree = new BinaryIndexedTree(N);

        int ans = 0;

        for (int i = 0; i < arr.length; i++) {

            Integer index = indexes.get(arr[i]);

            if (index == null)
                continue;

            int val;

            if (index == 0)
                val = 1;
            else
                val = tree.query(index - 1) + 1;

            ans = Math.max(ans, val);

            tree.update(index, val);
        }

        return target.length - ans;
    }

    public static class BinaryIndexedTree {

        private final int N;

        private final int[] data;

        public BinaryIndexedTree(int n) {
            N = n + 1;
            data = new int[N];
        }

        public void update(int index, int value) {
            int cur = index + 1;

            while (cur < data.length) {
                data[cur] = Math.max(data[cur], value);
                cur += lowbit(cur);
            }
        }

        public int query(int index) {
            int cur = index + 1;

            int max = 0;

            while (cur > 0) {
                max = Math.max(max, data[cur]);
                cur -= lowbit(cur);
            }

            return max;
        }

        private int lowbit(int x) {
            return x & -x;
        }

    }

    public static class SegmentTree {

        private final int N;

        private final int[] tree;

        public SegmentTree(int n) {
            this.N = n + 1;
            this.tree = new int[N << 2];
        }

        public int query(int L, int R) {
            return query(L + 1, R + 1, 1, N, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r)
                return tree[rt];

            int mid = l + ((r - l) >> 1);

            int left = 0;

            int right = 0;

            if (L <= mid)
                left = query(L, R, l, mid, leftChild(rt));
            if (R > mid)
                right = query(L, R, mid + 1, r, rightChild(rt));

            return Math.max(left, right);
        }

        public void update(int index, int value) {
            update(1, N, 1, index + 1, value);
        }

        private void update(int l, int r, int rt, int index, int value) {
            if (l == r && l == index) {
                tree[rt] = value;
                return;
            }

            int mid = l + ((r - l) >> 1);

            if (index <= mid)
                update(l, mid, leftChild(rt), index, value);
            else
                update(mid + 1, r, rightChild(rt), index, value);

            tree[rt] = Math.max(
                    tree[leftChild(rt)],
                    tree[rightChild(rt)]
            );
        }

        private int leftChild(int rt) {
            return rt << 1;
        }

        private int rightChild(int rt) {
            return rt << 1 | 1;
        }

    }

    public static int right(int[] target, int[] arr) {
        return target.length - longestCommonSequence(target, arr);
    }

    public static int longestCommonSequence(int[] arr1, int[] arr2) {

        int N = arr1.length;

        int M = arr2.length;

        int[] row1 = new int[M + 1];

        int[] row2 = new int[M + 1];

        int ans = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 1; j <= M; j++) {

                row2[j] = Math.max(row2[j - 1], row1[j]);

                if (arr1[i] == arr2[j - 1]) {
                    row2[j] = Math.max(row2[j], row1[j - 1] + 1);
                }

                ans = Math.max(ans, row2[j]);
            }

            int[] temp = row2;
            row2 = row1;
            row1 = temp;
            row2[0] = 0;
        }

        return ans;
    }

    /**
     * 未优化版
     */
    public static int raw(int[] arr1, int[] arr2) {

        int N = arr1.length;

        int M = arr2.length;

        int[][] dp = new int[N + 1][M + 1];

        int ans = 0;

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= M; j++) {

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                if (arr1[i - 1] == arr2[j - 1])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);

                ans = Math.max(ans, dp[i][j]);
            }

        }

        return ans;
    }

}
