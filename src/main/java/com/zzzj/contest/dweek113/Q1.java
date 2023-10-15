package com.zzzj.contest.dweek113;

import java.util.Arrays;
import java.util.List;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(minimumRightShifts(Arrays.asList(3, 4, 5, 1, 2)));

        System.out.println(minimumRightShifts(Arrays.asList(1, 3, 5)));

        System.out.println(minimumRightShifts(Arrays.asList(2, 1, 4)));

        // [31,72,79,25]
        System.out.println(minimumRightShifts(Arrays.asList(31, 72, 79, 25)));

    }

    public static int minimumRightShifts(List<Integer> nums) {

        int N = nums.size();

        for (int i = 1; i < N; i++) {

            // check order
            if (nums.get(i) < nums.get(i - 1)) {

                for (int j = i; j < N; j++) {
                    if (nums.get(j) > nums.get(0))
                        return -1;
                }

                for (int j = i + 1; j < N; j++) {
                    if (nums.get(j) < nums.get(j - 1))
                        return -1;
                }

                return N - i;
            }

        }

        return 0;
    }

}
