package com.zzzj.contest.week386;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Q3 {


    public static void main(String[] args) {

        System.out.println(earliestSecondToMarkIndices(new int[]{2, 2, 0}, new int[]{2, 2, 2, 2, 3, 2, 2, 1}));

        System.out.println(earliestSecondToMarkIndices(new int[]{2, 1}, new int[]{2, 1}));

        System.exit(0);

        System.out.println(earliestSecondToMarkIndices(new int[]{0, 1}, new int[]{1, 1, 1, 2, 2}));

        System.out.println(earliestSecondToMarkIndices(new int[]{1, 3}, new int[]{1, 1, 1, 2, 1, 1, 1}));

        System.out.println(earliestSecondToMarkIndices(new int[]{2, 4}, new int[]{1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2}));

        System.out.println(earliestSecondToMarkIndices(new int[]{2, 2, 0}, new int[]{2, 2, 2, 2, 3, 2, 2, 1}));

        System.out.println(earliestSecondToMarkIndices(new int[]{0, 1}, new int[]{2, 2, 2}));

        System.out.println(earliestSecondToMarkIndices(new int[]{2, 2}, new int[]{2, 2, 2}));

    }

    public static int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {

        int N = nums.length;

        int M = changeIndices.length;

        Set<Integer> vis = new HashSet<>();

        int l = 0;

        while (l < M && vis.size() < N) {
            vis.add(changeIndices[l]);
            l++;
        }

        if (vis.size() < N)
            return -1;

        int r = M;

        int ans = -1;

        while (l <= r) {

            int m = l + ((r - l) >> 1);

            if (check(nums, changeIndices, m)) {
                r = m - 1;
                ans = m;
            } else
                l = m + 1;

        }

        return ans;
    }

    public static boolean check(
            int[] nums,
            int[] changeIndices,
            int exp
    ) {

        int N = nums.length;

        int M = changeIndices.length;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        Set<Integer> vis = new HashSet<>();

        for (int i = exp - 1; i >= 0; i--)
            if (vis.add(changeIndices[i]))
                queue.add(i);

        int cost = 0;

        while (!queue.isEmpty()) {

            Integer nearest = queue.remove();

            cost += nums[changeIndices[nearest] - 1] + 1;

            if (cost > nearest + 1)
                return false;
        }

        return true;
    }

}

