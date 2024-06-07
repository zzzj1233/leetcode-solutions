package com.zzzj.window;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-06-04 11:17
 */
public class Leet719 {

    public static void main(String[] args) {

//        System.out.println(smallestDistancePair(new int[]{1, 2, 3, 4}, 3));

        System.out.println(smallestDistancePair(new int[]{4, 62, 100}, 2));

    }

    public static int smallestDistancePair(int[] nums, int k) {

        int N = nums.length;

        Arrays.sort(nums);

        int l = 0;

        int r = nums[N - 1] - nums[0];

        while (l <= r) {
            int m = l + ((r - l) >> 1);

            int c = check(nums, m);

            if (c >= k)
                r = m - 1;
            else
                l = m + 1;
        }

        return l;
    }

    public static int check(
            int[] nums,
            int expect
    ) {
        int N = nums.length;

        int cnt = 0;

        int left = 0;

        int right = 0;

        while (right < N) {

            while (left < N && nums[right] - nums[left] > expect) {
                left++;
            }

            cnt += right - left;

            right++;
        }

        return cnt;
    }
}
