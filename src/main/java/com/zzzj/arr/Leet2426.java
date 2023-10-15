package com.zzzj.arr;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

public class Leet2426 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            int M = 1000;

            int[] arr = ArrayUtil.generateArray(M);

            ArrayCopyIterator it = ArrayCopyIterator.fromArray(arr);

            if (reversePairs(it.next()) != right(it.next())) {
                System.out.println("Error");
                return;
            }

        }

        System.out.println("Ok");

    }

    private static class BinaryIndexedTree {
        private final long[] data;

        public BinaryIndexedTree(int N) {
            this.data = new long[N + 1];
        }

        public long query(int value) {
            int index = value + 1;
            int cnt = 0;
            while (index > 0) {
                cnt += data[index];
                index -= lowbit(index);
            }
            return cnt;
        }

        public void update(int value) {
            int index = value + 1;
            while (index < data.length) {
                data[index]++;
                index += lowbit(index);
            }
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

    public static long numberOfPairs(int[] nums1, int[] nums2, int diff) {

        int N = nums1.length;

        int MAX = 60001;

        BinaryIndexedTree tree = new BinaryIndexedTree(MAX);

        long ans = 0;

        for (int i = 0; i < N; i++) {

            int value = nums1[i] - nums2[i] + 30000;

            int limit = value + diff;

            ans += tree.query(limit);

            tree.update(value);
        }

        return ans;
    }

    static class BitTree {
        int n;
        int[] tree;

        BitTree() {
        }

        BitTree(int n_) {
            n = n_;
            tree = new int[n + 1];
        }

        int lowbit(int x) {
            return x & (-x);
        }

        void add(int idx, int val) {
            int i = idx + 1;
            while (i <= n) {
                tree[i] += val;
                i += lowbit(i);
            }
        }

        int query(int idx) {
            int i = idx + 1;
            int res = 0;
            while (1 <= i) {
                res += tree[i];
                i -= lowbit(i);
            }
            return res;
        }
    }

    public static long right(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        int[] dd = new int[n];
        for (int i = 0; i < n; i++) {
            dd[i] = nums1[i] - nums2[i] + 10000 * 3;
        }

        BitTree BT = new BitTree(10000 * 6 + 1);

        long res = 0L;
        for (int d : dd) {
            res += BT.query(d + diff);
            BT.add(d, 1);
        }

        return res;
    }

    public static int reversePairs(int[] record) {

        // 9, 7, 5, 4, 6

        // 4, 5, 6, 7, 9

        int[] sorted = Arrays.stream(record)
                .distinct()
                .sorted()
                .toArray();

        Arrays.sort(sorted);

        BinaryIndexedTree tree = new BinaryIndexedTree(sorted.length);

        int N = record.length;

        int ans = 0;

        for (int i = N - 1; i >= 0; i--) {

            int index = Arrays.binarySearch(sorted, record[i]);

            ans += tree.query(index - 1);

            tree.update(index);
        }

        return ans;
    }


    public static int right(int[] record) {
        int len = record.length;

        if (len < 2) {
            return 0;
        }

        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = record[i];
        }

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    private static int reversePairs(int[] record, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(record, left, mid, temp);
        int rightPairs = reversePairs(record, mid + 1, right, temp);

        if (record[mid] <= record[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(record, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    private static int mergeAndCount(int[] record, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = record[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            if (i == mid + 1) {
                record[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                record[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                record[k] = temp[i];
                i++;
            } else {
                record[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

}
