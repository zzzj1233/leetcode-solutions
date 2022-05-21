package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-05-09 10:48
 */
public class Leet259 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(500, -100, 100);
            int target = LeetUtils.random.nextInt(200) - 100;
            int[] copy1 = Arrays.copyOfRange(arr, 0, arr.length);
            int[] copy2 = Arrays.copyOfRange(arr, 0, arr.length);
            if (threeSumSmaller(arr, target) != right(copy1, target)) {
                System.out.println(Arrays.toString(copy2));
                System.out.println(target);
                System.out.println(threeSumSmaller(copy2, target));
                return;
            }
        }
    }

    public static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int tar = target - nums[i];

            // j + k < tar

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                if (nums[l] + nums[r] >= tar) {
                    r--;
                } else {
                    ans += r - l;
                    l++;
                    while (l < r && nums[l] == nums[l - 1]) {
                        ans += r - l;
                        l++;
                    }
                    r = nums.length - 1;
                }
            }

        }

        return ans;
    }

    public static int right(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private static int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }


}
