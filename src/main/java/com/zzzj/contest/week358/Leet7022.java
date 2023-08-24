package com.zzzj.contest.week358;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Leet7022 {


    public static void main(String[] args) {

        System.out.println(minAbsoluteDifference(Arrays.asList(4, 3, 2, 4), 2));

        System.out.println(minAbsoluteDifference(Arrays.asList(5, 3, 2, 10, 15), 1));

        System.out.println(minAbsoluteDifference(Arrays.asList(1, 2, 3, 4), 3));

    }

    public static int minAbsoluteDifference(List<Integer> nums, int x) {

        int N = nums.size();

        TreeSet<Integer> set = new TreeSet<>();

        int ans = Integer.MAX_VALUE;

        for (int i = x; i < N; i++) {
            set.add(nums.get(i - x));

            Integer num = nums.get(i);

            Integer floor = set.floor(num);
            Integer ceiling = set.ceiling(num);

            if (floor != null)
                ans = Math.min(ans, num - floor);
            if (ceiling != null)
                ans = Math.min(ans, ceiling - num);

        }

        return ans;
    }

}
