package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-11-06 17:31
 */
public class Leet1521 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            int N = 5000;

            int rangeR = N << 1;

            int[] arr = ArrayUtil.generateArray(N, 1, rangeR);

            int target = LeetUtils.random.nextInt(rangeR) + 1;

            int r = closestToTarget(arr, target);

            int rr = right(arr, target);

            if (r != rr) {
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("target = " + target);
                return;
            }

        }

        System.out.println("Ok");

    }

    public static int closestToTarget(int[] arr, int target) {

        int N = arr.length;

        int left = 0;

        int right = 0;

        int ans = Integer.MAX_VALUE;

        int val = -1;

        SegmentTree tree = new SegmentTree(arr);

        while (right < N) {

            val &= arr[right];

            ans = Math.min(ans, Math.abs(val - target));

            while (val < target && left < right) {
                left++;
                val = tree.query(left, right);
                ans = Math.min(ans, Math.abs(val - target));
            }

            right++;
        }

        return ans;
    }

    private static class SegmentTree {

        private final int[] and;

        private final int N;

        public SegmentTree(int[] arr) {
            this.N = arr.length + 1;
            this.and = new int[N << 2];
            this.buildTree(arr, 1, N - 1, 1);
        }

        private void buildTree(int[] arr, int l, int r, int rt) {
            if (l == r) {
                and[rt] = arr[l - 1];
                return;
            }

            int mid = l + ((r - l) >> 1);

            buildTree(arr, l, mid, rt << 1);

            buildTree(arr, mid + 1, r, rt << 1 | 1);

            and[rt] = and[rt << 1] & and[rt << 1 | 1];
        }

        private int query(int L, int R) {
            return query(L + 1, R + 1, 1, N - 1, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {

            if (L <= l && R >= r)
                return and[rt];

            int mid = l + ((r - l) >> 1);

            int left = -1;
            int right = -1;

            if (L <= mid)
                left = query(L, R, l, mid, rt << 1);
            if (R > mid)
                right = query(L, R, mid + 1, r, rt << 1 | 1);

            return left & right;
        }

    }

    private static int right(int[] arr, int target) {
        int ans = Math.abs(arr[0] - target);
        List<Integer> valid = new ArrayList<Integer>();
        valid.add(arr[0]);
        for (int num : arr) {
            List<Integer> validNew = new ArrayList<Integer>();
            validNew.add(num);
            int last = num;
            ans = Math.min(ans, Math.abs(num - target));
            for (int prev : valid) {
                int curr = prev & num;
                if (curr != last) {
                    validNew.add(curr);
                    ans = Math.min(ans, Math.abs(curr - target));
                    last = curr;
                }
            }
            valid = validNew;
        }
        return ans;
    }

}
