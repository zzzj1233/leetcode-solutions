package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-10 17:53
 */
public class Leet922 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArrayByParityII(new int[]{4, 2, 5, 7})));
    }

    public static int[] sortArrayByParityII(int[] nums) {

        int N = nums.length;

        int left = 0;

        int right = N - 1;

        while (left < N && right >= 0) {

            // 当前位置放了一个奇数
            if ((nums[left] & 1) == 1) {
                while ((nums[right] & 1) == 1) {
                    right -= 2;
                }
                swap(nums, left, right);
                right -= 2;
                left += 2;
            } else if ((nums[right] & 1) == 0) {
                while ((nums[left] & 1) == 0) {
                    left += 2;
                }
                swap(nums, left, right);
                right -= 2;
                left += 2;
            } else {
                right -= 2;
                left += 2;
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
