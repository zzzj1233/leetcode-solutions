package com.zzzj.arr;

import java.util.LinkedList;

public class Leet2149 {

    public static int[] rearrangeArray(int[] nums) {

        int N = nums.length;

        LinkedList<Integer> positive = new LinkedList<>();
        LinkedList<Integer> negative = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (nums[i] >= 0) {
                positive.add(i);
            } else {
                negative.add(i);
            }
        }

        int[] ans = new int[N];

        for (int i = 0; i < N; i += 2) {
            ans[i] = nums[positive.removeFirst()];
            ans[i + 1] = nums[negative.removeFirst()];
        }

        return ans;
    }

}
