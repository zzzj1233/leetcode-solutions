package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-06-07 21:28
 */
public class Leet2596 {

    public static void main(String[] args) {
        // System.out.println(findMagicIndex(new int[]{0, 2, 3, 4, 5}));
        System.out.println(findMagicIndex(new int[]{0, 0, 1}));
    }

    public static int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == i)
                return i;
            i = Math.max(nums[i], i + 1);
        }
        return -1;
    }

}
