package com.zzzj.review;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-03-09 17:54
 */
public class Leet31 {

    public static void main(String[] args) {
        int[] arr = {1, 5, 1};

        nextPermutation(arr);

        System.out.println(Arrays.toString(arr));
    }

    // [1,5,1]
    public static void nextPermutation(int[] nums) {
        int N = nums.length;

        int max = nums[N - 1];

        int index = -1;

        for (int i = N - 2; i >= 0; i--) {
            int num = nums[i];
            if (num >= max) {
                max = num;
            } else {
                index = i;
                break;
            }
        }

        // 3 2 1
        if (index == -1) {
            reverse(nums, 0, N - 1);
            return;
        }

        // find right
        int right = rightest(nums, index);

        swap(nums, index, right);

        // 排序
        Arrays.sort(nums, index + 1, N);
    }

    public static int rightest(int[] arr, int index) {
        int cur = arr[index];

        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] <= cur) {
                return i - 1;
            }
        }

        return arr.length - 1;
    }

    public static void reverse(int[] arr, int left, int right) {

        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }

    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
