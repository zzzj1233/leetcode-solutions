package com.zzzj.prefxisum;

/**
 * @author zzzj
 * @create 2021-12-09 16:49
 */
public class Leet724 {

    public static void main(String[] args) {
        // 1 8 11
        //
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }

    public static int pivotIndex(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return -1;
        }

        int[] prefixSum = new int[n];

        prefixSum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i];
        }

        int sum = 0;

        int total = prefixSum[n - 1];

        for (int i = 0; i < n; i++) {
            if (total - prefixSum[i] == sum) {
                return i;
            }
            sum += nums[i];
        }

        return -1;
    }


}
