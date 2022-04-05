package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-03 18:46
 */
public class Leet41 {

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1}));
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }

    public static int firstMissingPositive(int[] nums) {
        int L = 0;
        int R = nums.length - 1;

        while (L < R) {
            if (nums[L] == L + 1) {
                L++;
                continue;
            }
            if (nums[L] <= L || nums[L] > R + 1 || nums[L] == nums[nums[L] - 1]) {
                swap(nums, L, R);
                R--;
            } else {
                swap(nums, L, nums[L] - 1);
            }
        }

        return L + 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
