package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-06-28 20:39
 */
public class Leet1918 {

    public static void main(String[] args) {
        System.out.println(kthSmallestSubarraySum(new int[]{3, 3, 5, 5}, 7));
//        System.out.println(kthSmallestSubarraySum(new int[]{1, 2, 3}, 4));
    }

    public static int kthSmallestSubarraySum(int[] nums, int k) {

        int min = Integer.MAX_VALUE, sum = 0;

        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        int low = min, high = sum;

        while (low < high) {
            int mid = (high - low) / 2 + low;
            int count = countSubarrays(nums, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int countSubarrays(int[] nums, int threshold) {
        int count = 0;
        int sum = 0;
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            sum += nums[right];
            while (sum > threshold) {
                sum -= nums[left];
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }


}
