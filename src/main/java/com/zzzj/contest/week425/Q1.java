package com.zzzj.contest.week425;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-11-24 10:31
 */
public class Q1 {

    public static void main(String[] args) {

        System.out.println(minimumSumSubarray(Arrays.asList(3, -2, 1, 4), 2, 3));

        System.out.println(minimumSumSubarray(Arrays.asList(-2, 2, -3, 1), 2, 3));

        System.out.println(minimumSumSubarray(Arrays.asList(1, 2, 3, 4), 2, 4));
    }

    public static int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int N = nums.size();

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {

            int sum = 0;

            for (int j = i; j < N; j++) {

                int len = j - i + 1;

                if (len > r)
                    break;

                sum += nums.get(j);

                if (sum > 0 && len >= l)
                    ans = Math.min(ans, sum);
            }

        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
