package com.zzzj.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-01-24 11:47
 */
public class Leet15 {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        int left = -1;

        List<List<Integer>> ans = new LinkedList<>();

        // -1, -1, 0, 1, 2, -4
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur != left) {
                List<Integer> list = new ArrayList<>(3);
                left = cur;
                list.add(cur);
                int target = -cur;
                int[] ints = twoSum(nums, target, i + 1);
                if (ints != null) {
                    list.add(ints[0]);
                    list.add(ints[1]);
                    ans.add(list);
                }
            }

        }

        return ans;
    }

    public static int[] twoSum(int[] arr, int target, int l) {
        int r = arr.length - 1;

        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == target) {
                return new int[]{arr[l], arr[r]};
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }

        return null;
    }

}
