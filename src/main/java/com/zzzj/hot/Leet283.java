package com.zzzj.hot;

import com.zzzj.util.ArrayUtil;

/**
 * @author zzzj
 * @create 2022-04-24 11:54
 */
public class Leet283 {

    public static void moveZeroes(int[] nums) {
        int l = 0;
        int r = 0;

        // [0,1,0,3,12]
        // [1,3,12,0,0]

        while (r < nums.length) {
            if (nums[r] != 0) {
                ArrayUtil.swap(nums, r, l++);
            }
            r++;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
