package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-12-19 14:54
 */
public class Leet862 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            int M = 500;

            int k = Math.max(1, LeetUtils.random.nextInt(M) * LeetUtils.random.nextInt(M));

            int[] arr = ArrayUtil.generateArray(M, -M, M << 1);

            int r1 = shortestSubarray(arr, k);

            int r2 = right(arr, k);

            if (r1 != r2) {
                System.out.println("Error");
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("k = " + k);
                System.out.println("r1 = " + r1);
                System.out.println("r2 = " + r2);
                return;
            }

        }

        System.out.println("Ok");

        System.exit(0);

        System.out.println(shortestSubarray(new int[]{2, -1, 2}, 3));
        System.out.println(shortestSubarray(new int[]{1}, 1));
        System.out.println(shortestSubarray(new int[]{1, 2}, 4));
        System.out.println(shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167));
        System.out.println(shortestSubarray(new int[]{56, -21, 56, 35, -9}, 61));
    }

    public static int shortestSubarray(int[] nums, int k) {

        int N = nums.length;

        long[] preSum = new long[N + 1];

        int ans = Integer.MAX_VALUE;

        SegmentTree tree = new SegmentTree(preSum);

        tree.update(0, 0);

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            long expect = preSum[i] - k;
            tree.update(i, preSum[i]);
            // 二分
            int left = binarySearch(expect, tree, i, preSum);
            if (left != -1)
                ans = Math.min(ans, i - left);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int binarySearch(long expect, SegmentTree tree, int limit, long[] preSum) {
        int res = -1;

        int left = 0;
        int right = limit;

        while (left < right) {

            int mid = left + ((right - left) >> 1);

            int leftMin = -1, rightMin = -1;

            if (left <= mid)
                leftMin = tree.query(left, mid);
            if (mid + 1 <= right)
                rightMin = tree.query(mid + 1, right);

            if (rightMin != -1 && preSum[rightMin] <= expect) {
                left = mid + 1;
                res = rightMin;
            } else if (leftMin != -1 && preSum[leftMin] <= expect) {
                right = mid;
                res = leftMin;
            } else {
                break;
            }

        }

        return res;
    }

    private static class SegmentTree {

        private final int[] tree;

        private final int N;

        private final long[] preSum;

        private SegmentTree(long[] preSum) {
            this.N = preSum.length + 1;
            this.tree = new int[N << 2];
            this.preSum = preSum;
        }

        public int query(int L, int R) {
            return query(L + 1, R + 1, 1, N, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r)
                return tree[rt];

            int mid = l + ((r - l) >> 1);

            int min = -1;

            if (L <= mid)
                min = query(L, R, l, mid, leftChild(rt));
            if (R > mid) {
                int right = query(L, R, mid + 1, r, rightChild(rt));
                if (right != -1)
                    if (min == -1 || preSum[right] <= preSum[min])
                        min = right;
            }

            return min;
        }

        public void update(int index, long value) {
            update(index + 1, 1, N, 1);
        }

        private void update(int index, int l, int r, int rt) {
            if (l == r && l == index) {
                tree[rt] = index - 1;
                return;
            }

            int mid = l + ((r - l) >> 1);

            if (index <= mid)
                update(index, l, mid, leftChild(rt));
            else
                update(index, mid + 1, r, rightChild(rt));

            int left = tree[leftChild(rt)];
            int right = tree[rightChild(rt)];
            tree[rt] = preSum[right] <= preSum[left] ? right : left;
        }

        private int leftChild(int rt) {
            return rt << 1;
        }

        private int rightChild(int rt) {
            return rt << 1 | 1;
        }
    }

    private static int right(int[] nums, int k) {
        int N = nums.length;

        int[] preSum = new int[N + 1];

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];

            int expect = preSum[i] - k;

            for (int j = i - 1; j >= 0; j--) {
                if (preSum[j] <= expect) {
                    ans = Math.min(ans, i - j);
                    break;
                }
            }

        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
