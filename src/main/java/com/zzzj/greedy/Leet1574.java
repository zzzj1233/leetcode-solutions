package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-12-26 11:52
 */
public class Leet1574 {

    public static void main(String[] args) {

        System.out.println(findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 0, 7, 8, 9}));

        // System.out.println(findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 4, 2, 3, 5}));

    }

    public static int findLengthOfShortestSubarray(int[] arr) {

        int N = arr.length;

        int right = N - 1;

        while (right - 1 >= 0 && arr[right - 1] <= arr[right])
            right--;

        // right - N 有序
        if (right == 0)
            return 0;

        int left = 0;

        while (left + 1 < right && arr[left + 1] >= arr[left])
            left++;

        int ans = N - left - 1;

        int r = N - 2;

        while (r >= right - 1) {

            // 考虑r作为子数组的右端点

            while (left >= 0 && arr[left] > arr[r + 1])
                left--;

            ans = Math.min(ans, r - left);

            r--;
        }

        return ans;
    }

}
