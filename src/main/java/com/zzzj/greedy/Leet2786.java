package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-03 16:40
 */
public class Leet2786 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};

        wiggleSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void wiggleSort(int[] nums) {

        Arrays.sort(nums);

        int N = nums.length;

        int[] origin = Arrays.copyOfRange(nums, 0, nums.length);

        int left = 0;

        int right = N - 1;

        int index = 0;

        while (left < right) {

            nums[index] = origin[right];
            index++;
            nums[index] = origin[left];
            index++;

            left++;
            right--;
        }

        if (left == right) {
            nums[index] = origin[left];
        }
    }


}
