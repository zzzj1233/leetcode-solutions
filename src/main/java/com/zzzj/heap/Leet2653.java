package com.zzzj.heap;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-17 15:34
 */
public class Leet2653 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(getSubarrayBeauty(new int[]{1, -1, -3, -2, 3}, 3, 2)));

        System.out.println(Arrays.toString(getSubarrayBeauty(new int[]{-1, -2, -3, -4, -5}, 2, 2)));

        System.out.println(Arrays.toString(getSubarrayBeauty(new int[]{-3, 1, 2, -3, 0, -3}, 2, 1)));

    }

    public static int[] getSubarrayBeauty(int[] nums, int k, int x) {

        int[] bucket = new int[101];

        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            bucket[nums[i] + 50]++;
        }

        ans[0] = find(bucket, x);

        for (int i = 1; i < ans.length; i++) {
            bucket[nums[i - 1] + 50]--;
            bucket[nums[i + k - 1] + 50]++;
            ans[i] = find(bucket, x);
        }

        return ans;
    }

    public static int find(int[] bucket, int x) {
        int cur = 0;

        for (int i = 0; i < bucket.length; i++) {
            cur += bucket[i];
            if (cur >= x)
                return Math.min(i - 50, 0);
        }

        return -1;
    }

}
