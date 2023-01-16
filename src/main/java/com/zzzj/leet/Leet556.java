package com.zzzj.leet;

import cn.hutool.core.util.RandomUtil;

public class Leet556 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 100000; i++) {
            int num = RandomUtil.randomInt(1, Integer.MAX_VALUE / 2);
            if (nextGreaterElement(num) != solution.nextGreaterElement(num)) {
                System.out.println("Error");
                System.out.println(num);
                System.out.println(nextGreaterElement(num));
                System.out.println(solution.nextGreaterElement(num));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int nextGreaterElement(int n) {

        String num = String.valueOf(n);

        int N = num.length();

        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Character.digit(num.charAt(i), 10);
        }

        int maxIndex = N - 1;

        int index = -1;

        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[maxIndex]) {
                index = i;
                break;
            } else if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        if (index == -1) {
            return -1;
        }

        int minIndex = maxIndex;

        for (int i = index + 1; i < N; i++) {
            if (nums[i] > nums[index] && nums[i] <= nums[minIndex]) {
                minIndex = i;
            }
        }

        char[] chars = num.toCharArray();

        swap(chars, index, minIndex);

        reverse(chars, index + 1, N - 1);

        long ans = Long.parseLong(new String(chars));

        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    public static void reverse(char[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }


    public static void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static class Solution {
        public int nextGreaterElement(int n) {
            char[] nums = Integer.toString(n).toCharArray();
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i < 0) {
                return -1;
            }

            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
            reverse(nums, i + 1);
            long ans = Long.parseLong(new String(nums));
            return ans > Integer.MAX_VALUE ? -1 : (int) ans;
        }

        public void reverse(char[] nums, int begin) {
            int i = begin, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        public void swap(char[] nums, int i, int j) {
            char temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
