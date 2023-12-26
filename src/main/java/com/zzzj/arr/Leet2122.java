package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-07 11:59
 */
public class Leet2122 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(recoverArray(new int[]{2, 10, 6, 4, 8, 12})));

        System.out.println(Arrays.toString(recoverArray(new int[]{1, 1, 3, 3})));

        System.out.println(Arrays.toString(recoverArray(new int[]{5, 435})));

    }

    public static int[] recoverArray(int[] nums) {

        int N = nums.length;

        Arrays.sort(nums);

        boolean[] visited = new boolean[N];

        int[] ans = new int[N / 2];

        for (int i = 1; i < N; i++) {

            int diff = nums[i] - nums[0];

            if (diff > 0 && diff % 2 == 0 && check(nums, diff, visited, ans))
                return ans;

        }

        return null;
    }

    private static boolean check(int[] nums, int expect, boolean[] visited, int[] ans) {

        int N = nums.length;

        Arrays.fill(visited, false);

        int ansIndex = 0;

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;

            visited[i] = true;

            int next = binarySearch(nums, nums[i] + expect, visited);

            if (next == -1)
                return false;

            ans[ansIndex] = nums[i] + expect / 2;
            ansIndex++;
        }

        return true;
    }

    private static int binarySearch(int[] nums, int target, boolean[] visited) {

        int left = 0;

        int right = nums.length - 1;

        int res = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] == target) {
                if (visited[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                    res = mid;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        if (res != -1)
            visited[res] = true;

        return res;
    }

}
