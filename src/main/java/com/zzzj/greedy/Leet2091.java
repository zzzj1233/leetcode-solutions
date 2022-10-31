package com.zzzj.greedy;

public class Leet2091 {

    public static int minimumDeletions(int[] nums) {

        int minIndex = 0;
        int maxIndex = 0;

        int N = nums.length;

        for (int i = 1; i < N; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            } else if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }


        // case1 : 从左边删除两个元素

        // case2 : 从右边删除两个元素

        // case3 : 从两边删除两个元素

        int case1 = Math.max(minIndex, maxIndex);
        int case2 = N - Math.min(minIndex, maxIndex);
        int case3 = Math.min(minIndex, maxIndex) + (N - Math.max(minIndex, maxIndex));

        return Math.min(case1, Math.min(case2, case3));

    }

}
