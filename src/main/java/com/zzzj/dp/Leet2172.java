package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-01-09 10:47
 */
public class Leet2172 {

    public static void main(String[] args) {

        System.out.println(maximumANDSum(new int[]{14, 7, 9, 8, 2, 4, 11, 1, 9}, 8));

        System.out.println(maximumANDSum(new int[]{1, 2, 3, 4, 5, 6}, 3));

        System.out.println(maximumANDSum(new int[]{1, 3, 10, 4, 7, 1}, 9));

    }

    public static int maximumANDSum(int[] nums, int numSlots) {

        int N = nums.length;

        int limit = 1 << N;

        int[][] f = new int[(numSlots << 1) + 1][limit];

        for (int i = 1; i <= numSlots << 1; i++) {

            int bitCnt = i << 1;

            for (int stat = 0; stat < limit; stat++) {

                f[i][stat] = f[i - 1][stat];

                for (int bit1 = 0; bit1 < N; bit1++) {

                    if ((stat & (1 << bit1)) != 0) {

                        int prevStat = stat ^ (1 << bit1);

                        f[i][stat] = Math.max(
                                f[i][stat],
                                f[i - 1][prevStat] + (nums[bit1] & ((i % numSlots) + 1))
                        );

                    }
                }

            }

        }

        return f[numSlots << 1][limit - 1];
    }

}
