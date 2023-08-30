package com.zzzj.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-08-24 17:29
 */
public class Leet2602 {

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{3, 1, 6, 8}, new int[]{1, 5}));

        System.out.println(minOperations(new int[]{2, 9, 6, 3}, new int[]{10, 5, 4, 7}));
    }

    public static List<Long> minOperations(int[] nums, int[] queries) {

        Arrays.sort(nums);

        int N = nums.length;

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int M = queries.length;

        List<Long> ans = new ArrayList<>(M);

        for (int i = 0; i < M; i++) {
            int query = queries[i];

            int left = 0;

            int right = N - 1;

            int m = N;

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] >= query) {
                    m = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // [ 0 - m - 1 ] < query
            long leftSum = preSum[m];
            int leftCnt = m;

            long result = (long) leftCnt * query - leftSum;

            long rightSum = preSum[N] - preSum[m];
            int rightCnt = N - m;

            result += rightSum - (long) rightCnt * query;

            ans.add(result);
        }

        return ans;
    }


}
