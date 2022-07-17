package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-07-10 17:48
 */
public class Leet905 {

    public static int[] sortArrayByParity(int[] nums) {
        int N = nums.length;

        int left = 0;
        int right = N - 1;

        while (left < right) {

            if ((nums[right] & 1) == 0) {
                swap(nums, right, left);
                left++;
            } else {
                right--;
            }
        }


        return nums;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
