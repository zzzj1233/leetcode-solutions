package com.zzzj.contest.dweek116;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(sumCounts(Arrays.asList(1, 2, 1)));

        System.out.println(sumCounts(Arrays.asList(2, 2)));

    }

    public static int sumCounts(List<Integer> nums) {

        final int MOD = 1000000007;

        int N = nums.size();

        long ans = 0;

        for (int i = 0; i < N; i++) {

            Set<Integer> cnt = new HashSet<>();

            for (int j = i; j < N; j++) {

                cnt.add(nums.get(j));

                ans = (ans + (long) cnt.size() * cnt.size()) % MOD;
            }

        }

        return (int) ans;
    }

}
