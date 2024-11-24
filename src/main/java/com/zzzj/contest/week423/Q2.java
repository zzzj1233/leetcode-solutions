package com.zzzj.contest.week423;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-11-10 10:42
 */
public class Q2 {

    public static void main(String[] args) {


        System.out.println(maxIncreasingSubarrays(Arrays.asList(2, 5, 7, 8, 9, 2, 3, 4, 3, 1)));

        System.out.println(maxIncreasingSubarrays(Arrays.asList(1, 2, 3, 4, 4, 4, 4, 5, 6, 7)));

        System.out.println(maxIncreasingSubarrays(Arrays.asList(-15, 19)));


    }

    public static int maxIncreasingSubarrays(List<Integer> nums) {

        int N = nums.size();

        int ans = 0;

        int start = 0;

        int leftMax = 0;

        for (int i = 1; i < N; i++) {

            if (nums.get(i) <= nums.get(i - 1)) {
                int len = i - start;
                ans = Math.max(ans, Math.max(
                        Math.max(len / 2, leftMax / 2),
                        Math.min(leftMax, len)
                ));
                leftMax = len;
                start = i;
            }

        }

        int len = N - start;

        ans = Math.max(ans, Math.max(
                Math.max(len / 2, len / 2),
                Math.min(leftMax, len)
        ));

        return ans;
    }

}
