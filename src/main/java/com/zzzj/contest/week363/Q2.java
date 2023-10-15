package com.zzzj.contest.week363;

import java.util.Arrays;
import java.util.List;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(countWays(Arrays.asList(6, 0, 3, 3, 6, 7, 2, 7)));

        System.out.println(countWays(Arrays.asList(1, 1, 0, 1)));

    }

    public static int countWays(List<Integer> list) {

        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }


        return -1;
    }

}
