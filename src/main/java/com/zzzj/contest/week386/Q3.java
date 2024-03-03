package com.zzzj.contest.week386;

public class Q3 {


    public static void main(String[] args) {

        // [2,4]
        // [1,2,1,2,1,1,2,1,1,1,2]
        System.out.println(earliestSecondToMarkIndices(new int[]{2, 4}, new int[]{1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2}));

        System.out.println(earliestSecondToMarkIndices(new int[]{0, 1}, new int[]{1, 1, 1, 2, 2}));

        System.out.println(earliestSecondToMarkIndices(new int[]{2, 2, 0}, new int[]{2, 2, 2, 2, 3, 2, 2, 1}));

        System.out.println(earliestSecondToMarkIndices(new int[]{1, 3}, new int[]{1, 1, 1, 2, 1, 1, 1}));

        System.out.println(earliestSecondToMarkIndices(new int[]{0, 1}, new int[]{2, 2, 2}));

        System.out.println(earliestSecondToMarkIndices(new int[]{2, 2}, new int[]{2, 2, 2}));

    }

    public static int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {

        int N = nums.length;

        int M = changeIndices.length;

        return -1;
    }

}

