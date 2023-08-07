package com.zzzj.contest.dweek105;

/**
 * @author zzzj
 * @create 2023-07-31 16:19
 */
public class Leet2708 {

    public static void main(String[] args) {

        System.out.println(maxStrength(new int[]{3, -1, -5, 2, 5, -9}));

        System.out.println(maxStrength(new int[]{-4, -5, -4}));

        System.out.println(maxStrength(new int[]{0, -1}));

    }

    public static long maxStrength(int[] nums) {

        long maxPositive = 0;
        long maxNegative = 0;

        int N = nums.length;

        if (N == 1) return nums[0];

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            long mp = Math.max(Math.max(maxPositive, num), Math.max(
                    maxPositive * num,
                    maxNegative * num
            ));

            long mn = Math.min(Math.min(maxNegative, num),
                    Math.min(
                            maxNegative * num,
                            maxPositive * num
                    )
            );

            maxPositive = mp;
            maxNegative = mn;
        }

        return maxPositive;
    }

}
