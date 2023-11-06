package com.zzzj.dp;

import java.util.stream.IntStream;

/**
 * @author zzzj
 * @create 2023-11-06 11:23
 */
public class Leet2906 {

    public static void main(String[] args) {

        System.out.println(maxBalancedSubsequenceSum(new int[]{3, 3, 5, 6}));

        System.out.println(maxBalancedSubsequenceSum(new int[]{5, -1, -3, 8}));

        System.out.println(maxBalancedSubsequenceSum(new int[]{-2, -1}));

        System.out.println(maxBalancedSubsequenceSum(new int[]{-1, 2, -5, 5, -2, 6, -5, 2}));

    }

    public static long maxBalancedSubsequenceSum(int[] nums) {

        int N = nums.length;

        int[] sorted = IntStream.range(0, N)
                .map(index -> nums[index] - index)
                .sorted()
                .toArray();

        long ans = Integer.MIN_VALUE;

        BIT tree = new BIT(N);

        for (int i = 0; i < N; i++) {

            int index = binarySearch(sorted, nums[i] - i);

            long max = tree.query(index) + nums[i];

            tree.update(index, max);

            ans = Math.max(ans, max);
        }

        return ans;
    }

    private static int binarySearch(int[] sorted, int value) {
        int left = 0;
        int right = sorted.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (sorted[mid] == value) {
                ans = mid;
                left = mid + 1;
            } else if (sorted[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static class BIT {

        private final long[] data;

        private BIT(int N) {
            this.data = new long[N + 1];
        }

        private void update(int i, long value) {
            int index = i + 1;
            while (index < data.length) {
                data[index] = Math.max(data[index], value);
                index += lowbit(index);
            }
        }

        private long query(int i) {
            int index = i + 1;
            long res = 0;
            while (index > 0) {
                res = Math.max(res, data[index]);
                index -= lowbit(index);
            }
            return res;
        }

        private int lowbit(int x) {
            return x & -x;
        }

    }

}
