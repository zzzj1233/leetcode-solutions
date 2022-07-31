package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-09 16:19
 */
public class Leet280 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        wiggleSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;


        int temp = nums[0];

        // temp = nums + 1
        // nums + 1 = arr[right - 1]

        // left +=2

        int mid = nums[nums.length / 2];

        while (left <= right) {
            int rightValue = nums[right];
            nums[left] = temp;
            temp = nums[left + 1];
            nums[left + 1] = rightValue;
            left += 2;
            right -= 1;
        }

        if (nums.length % 2 != 0) {
            nums[left] = mid;
        }

    }


}
