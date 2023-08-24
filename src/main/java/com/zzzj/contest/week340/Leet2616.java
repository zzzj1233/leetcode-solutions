package com.zzzj.contest.week340;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Leet2616 {


    public static void main(String[] args) {


        System.out.println(minimizeMax(new int[]{3, 4, 2, 3, 2, 1, 2}, 3));

    }

    public static int minimizeMax(int[] nums, int p) {

        int N = nums.length;

        Arrays.sort(nums);

        int[][] diff = new int[N - 1][3];

        for (int i = 1; i < N; i++) {
            diff[i - 1][0] = nums[i] - nums[i - 1];
            diff[i - 1][1] = i;
            diff[i - 1][2] = i - 1;
        }

        Arrays.sort(diff, Comparator.comparingInt(o -> o[0]));

        Set<Integer> removed = new HashSet<>();

        int ans = 0;

        for (int[] items : diff) {
            if (p == 0) break;

            if (removed.contains(items[1]) || removed.contains(items[2])) continue;

            ans = Math.max(ans, Math.abs(items[0]));
            p--;

            removed.add(items[1]);
            removed.add(items[2]);
        }

        return ans;
    }

}
