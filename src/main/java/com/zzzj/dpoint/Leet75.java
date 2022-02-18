package com.zzzj.dpoint;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-21 16:38
 */
public class Leet75 {

    public static void main(String[] args) {
        sortColors(new int[]{1});
    }

    public static void sortColors(int[] nums) {
        sort(nums, 2, sort(nums, 1, sort(nums, 0, 0)));
        System.out.println(Arrays.toString(nums));
    }

    private static int sort(int[] nums, int num, int i) {
        int j = i;
        while (j < nums.length) {
            if (nums[j] == num) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
            j++;
        }
        return i;
    }

}
