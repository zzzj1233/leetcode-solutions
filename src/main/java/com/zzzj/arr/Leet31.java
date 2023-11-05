package com.zzzj.arr;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-04-03 14:02
 */
public class Leet31 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 0, 100);
            printNextPermutation(arr);
        }
    }

    public static void printNextPermutation(int[] nums) {
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
        int[] copy2 = Arrays.copyOfRange(nums, 0, nums.length);
        right(nums);
        nextPermutation(copy);

        if (!Arrays.equals(nums, copy)) {
            System.out.println("Error");
            System.out.println(Arrays.toString(copy2));
            System.out.println(Arrays.toString(nums));
            System.out.println(Arrays.toString(copy));
            System.out.println("==================== \n");
            return;
        }
    }


    public static void nextPermutation(int[] nums) {
        // 1. 找到最后一个比当前数大的元素
        int L = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                L = i - 1;
            }
        }

        if (L == -1) {
            reverse(nums);
            return;
        }

        // 下一个数比当前元素大

        // 2. 找到后面的最小的一个元素
        int min = L + 1;

        for (int i = L + 2; i < nums.length; i++) {
            if (nums[i] <= nums[min] && nums[i] > nums[L]) {
                min = i;
            }
        }


        // 3. swap
        swap(nums, L, min);

        Arrays.sort(nums, L + 1, nums.length);
    }


    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
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

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void right(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

}
