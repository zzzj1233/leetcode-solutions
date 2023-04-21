package com.zzzj.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-09 15:59
 */
public class Leet15 {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, -1, 0, 1, 2, -4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        int N = nums.length;

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            if (i > 0 && num == nums[i - 1]) {
                continue;
            }

            // 剪枝
            if (num > 0) {
                break;
            }

            search(nums, i + 1, num, ans);

        }

        return ans;
    }

    public static void search(int[] nums, int left, int num, List<List<Integer>> ans) {
        int N = nums.length;

        int right = N - 1;

        int target = -num;

        while (left < right) {

            int sum = nums[left] + nums[right];

            if (sum == target) {

                ans.add(Arrays.asList(nums[left], nums[right], num));

                left++;
                right--;

                // 去重
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }

                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }

                continue;
            }

            if (sum > target) {
                right--;
            } else {
                left++;
            }

        }

    }

}
