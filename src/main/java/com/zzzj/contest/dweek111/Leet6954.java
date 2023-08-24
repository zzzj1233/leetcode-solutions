package com.zzzj.contest.dweek111;

import java.util.Arrays;
import java.util.List;

public class Leet6954 {

    public static void main(String[] args) {

        System.out.println(countPairs(Arrays.asList(-1, 1, 2, 3, 1), 2));

        System.out.println(countPairs(Arrays.asList(-6, 2, 5, -2, -7, -1, 3), -2));

    }

    public static int countPairs(List<Integer> nums, int target) {

        int N = nums.size();

        int ans = 0;

        for (int i = 1; i < N; i++) {

            for (int j = 0; j < i; j++) {
                if (nums.get(j) + nums.get(i) < target)
                    ans++;
            }

        }

        return ans;
    }

}
