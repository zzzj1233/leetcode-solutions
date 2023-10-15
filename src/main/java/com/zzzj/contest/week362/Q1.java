package com.zzzj.contest.week362;

import com.zzzj.leet.LeetUtils;

import java.util.List;

public class Q1 {


    public static void main(String[] args) {

        System.out.println(numberOfPoints(LeetUtils.convertLists("[[3,6],[1,5],[4,7]]")));

        System.out.println(numberOfPoints(LeetUtils.convertLists("[[1,3],[5,8]]")));

    }

    public static int numberOfPoints(List<List<Integer>> nums) {
        int ans = 0;

        nums.sort((o1, o2) -> {
            int diff = o1.get(0).compareTo(o2.get(0));
            return diff == 0 ? o2.get(1).compareTo(o1.get(1)) : diff;
        });

        int N = nums.size();

        int start = 0;

        for (int i = 0; i < N; i++) {
            start = Math.max(start, nums.get(i).get(0));
            ans += Math.max(0, nums.get(i).get(1) - start + 1);
            start = Math.max(start, nums.get(i).get(1) + 1);
        }

        return ans;
    }

}
