package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-02-28 15:00
 */
public class Leet2294 {

    public static void main(String[] args) {
//        System.out.println(partitionArray(new int[]{3, 6, 1, 2, 5}, 2));

//        System.out.println(partitionArray(new int[]{1, 2, 3}, 1));

        System.out.println(partitionArray(new int[]{2, 2, 4, 5}, 0));
    }

    public static int partitionArray(int[] nums, int k) {

        Arrays.sort(nums);

        int index = 0;

        int N = nums.length;

        int ans = 1;

        while (index < N) {
            if (nums[N - 1] - nums[index] <= k) {
                return ans;
            }
            // 二分
            int searchIndex = binSearch(nums, index, k);
            ans++;
            index = searchIndex + 1;
        }

        return ans;
    }

    public static int binSearch(int[] nums, int index, int k) {
        int right = nums.length - 1;

        int left = index;

        int result = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] - nums[index] <= k) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

}
