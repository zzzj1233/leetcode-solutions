package com.zzzj.dpoint;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-04-03 14:02
 */
public class Leet31 {

    public static void main(String[] args) {
        printNextPermutation(new int[]{1, 2, 3});
        printNextPermutation(new int[]{3, 2, 1});
        printNextPermutation(new int[]{1, 1, 5});
        printNextPermutation(new int[]{1, 3, 4, 3, 2, 0});
        printNextPermutation(new int[]{1, 3, 2, 2, 2});
    }

    public static void printNextPermutation(int[] nums) {
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {

        // 3 2 1 确保 i == 0
        // 1 2 3 4 确保 i == 3
        // 1 3 2 确保 i == 0

        int L = -1;
        int R = -1;

        OUTER:
        for (int i = nums.length - 1; i > 0; i--) {

            if (nums[i] == 0) {
                continue;
            }

            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    L = j;
                    R = i;
                    break OUTER;
                }
            }

        }

        if (L == -1) {
            reverse(nums);
        } else {
            swap(nums, L, R);
            Arrays.sort(nums, L + 1, nums.length);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums) {
        int L = 0;
        int R = nums.length - 1;

        while (L < R) {
            swap(nums, L, R);
            L++;
            R--;
        }
    }

}
