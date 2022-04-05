package com.zzzj.hot;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-04-17 20:21
 */
public class Leet189 {

    public static void main(String[] args) {
        int[] arr = {-1, -100, 3, 99};

        rotate(arr, 2);

        System.out.println(Arrays.toString(arr));
    }

    //  1 2 3 4 5 6 7 , k = 3
    //  5 6 7 1 2 3 4
    public static void rotate(int[] nums, int k) {
        if (k >= nums.length) {
            return;
        }

        reverse(nums, 0, nums.length - 1);
        // 4 3 2 1 5 6 7
        reverse(nums, 0, k - 1);
        // 4 3 2 1 7 6 5
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
