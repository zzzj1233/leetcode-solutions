package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-21 17:36
 */
public class Leet26 {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 0, 1, 1, 1};
        System.out.println(removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

    // 0,0,1,1,1,2,2,3,3,4
    public static int removeDuplicates(int[] nums) {
        int ans = nums.length;

        int i = 1;
        int j = 1;

        // 0 1 1
        // 0 1 2 3 4
        while (j < nums.length) {

            while (j < nums.length && nums[j] == nums[j - 1]) {
                ans--;
                j++;
            }

            if (j < nums.length) {
                nums[i] = nums[j];
                i++;
                j++;
            }

        }

        return ans;
    }

}
