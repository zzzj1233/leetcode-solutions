package com.zzzj.dpoint;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-21 16:46
 */
public class Leet80 {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 0, 1, 2, 2, 4, 4};

        System.out.println(removeDuplicates(arr));

        System.out.println(Arrays.toString(arr));
    }


    // 每个重复的元素最多出现两次
    public static int removeDuplicates(int[] nums) {
        int ans = nums.length;
        // 0 0 0 1 1 1 1

        int i = 2;
        int j = 2;

        while (j < nums.length) {

            while (j < nums.length && nums[j] == nums[j - 2]) {
                j++;
                ans--;
            }

            if (j < nums.length) {
                int temp = nums[j];
                j++;
                while (j < nums.length && nums[j] == nums[j - 2]) {
                    j++;
                    ans--;
                }
                nums[i] = temp;
                i++;
                if (j < nums.length) {
                    nums[i] = nums[j];
                    i++;
                    j++;
                }
            }

        }

        return ans;
    }

}
