package com.zzzj.contest.week344;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-08-01 17:52
 */
public class Leet2670 {


    public static void main(String[] args) {

        System.out.println(Arrays.toString(distinctDifferenceArray(new int[]{1, 2, 3, 4, 5})));

        System.out.println(Arrays.toString(distinctDifferenceArray(new int[]{3, 2, 3, 4, 2})));

    }

    public static int[] distinctDifferenceArray(int[] nums) {

        int N = nums.length;

        int[] left = new int[N];

        Set<Integer> set = new HashSet<>(N);

        for (int i = 0; i < N; i++) {
            set.add(nums[i]);
            left[i] = set.size();
        }

        set.clear();

        int[] right = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            set.add(nums[i]);
            right[i] = set.size();
        }

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            ans[i] = left[i] - right[i + 1];
        }

        return ans;
    }

}
