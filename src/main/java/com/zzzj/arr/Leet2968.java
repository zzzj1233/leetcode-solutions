package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-12-27 17:54
 */
public class Leet2968 {

    public static int maxFrequencyScore(int[] nums, long k) {

        int N = nums.length;

        Arrays.sort(nums);

        int left = 0;

        int right = 0;

        int ans = 0;

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++)
            preSum[i] = preSum[i - 1] + nums[i - 1];

        while (right < N) {

            // l - r的中位数
            int middle = nums[left + ((right - left) >> 1)];

            // 检查cost是否大于k
            while (left < right && cost(preSum, nums, left, right) > k)
                left++;

            ans = Math.max(ans, right - left + 1);

            right++;
        }

        return ans;
    }

    // [ left - right ]
    private static long cost(long[] preSum, int[] nums, int left, int right) {

        int mid = left + ((right - left) >> 1);

        long midNum = nums[mid];

        int leftCnt = mid - left;
        int rightCnt = right - mid;

        long r = (preSum[right + 1] - preSum[mid + 1]) - midNum * rightCnt;

        long l = midNum * leftCnt - (preSum[mid] - preSum[left]);

        return r + l;
    }

}
