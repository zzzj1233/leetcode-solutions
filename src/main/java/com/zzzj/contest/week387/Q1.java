package com.zzzj.contest.week387;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1 {

    public static int[] resultArray(int[] nums) {

        int N = nums.length;

        List<Integer> left = new ArrayList<>(N);

        List<Integer> right = new ArrayList<>(N);

        left.add(nums[0]);
        right.add(nums[1]);

        for (int i = 2; i < N; i++) {
            if (left.get(left.size() - 1) > right.get(right.size() - 1))
                left.add(nums[i]);
            else
                right.add(nums[i]);
        }

        left.addAll(right);

        return left.stream().mapToInt(value -> value).toArray();
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(resultArray(new int[]{2, 1, 3})));

        System.out.println(Arrays.toString(resultArray(new int[]{5, 4, 3, 8})));

    }

}
