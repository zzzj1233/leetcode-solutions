package com.zzzj.daily;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-10 14:07
 */
public class Leet16 {

    public static void main(String[] args) {

        // [-55,-24,-18,-11,-7,-3,4,5,6,9,11,23,33]
        //0
        System.out.println(threeSumClosest(new int[]{-55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33}, 0));

    }

    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        int N = nums.length;

        for (int i = 0; i < N; i++) {

            for (int j = i + 2; j < N; j++) {

                int closest = binSearch(nums, i, j, target - nums[i] - nums[j]);

                if (nums[i] + nums[j] + closest == target) {
                    return target;
                }

                if (Math.abs(nums[i] + nums[j] + closest - target) <= Math.abs(ans - target)) {
                    ans = nums[i] + nums[j] + closest;
                }

            }

        }

        return ans;
    }

    public static int binSearch(int[] nums, int L, int R, int expect) {

        int ret = Integer.MAX_VALUE;

        int left = L + 1;

        int right = R - 1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            int diff;

            if (nums[mid] == expect) {
                return nums[mid];
            } else if (nums[mid] > expect) {
                diff = nums[mid] - expect;
                right = mid - 1;
            } else {
                diff = expect - nums[mid];
                left = mid + 1;
            }

            if (diff < Math.abs(ret - expect)) {
                ret = nums[mid];
            }
        }

        return ret;
    }
}
