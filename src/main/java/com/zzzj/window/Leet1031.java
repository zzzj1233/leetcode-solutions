package com.zzzj.window;

/**
 * @author Zzzj
 * @create 2021-12-18 16:25
 */
public class Leet1031 {

    public static void main(String[] args) {
        System.out.println(maxSumTwoNoOverlap(new int[]{0, 6, 5, 2, 2, 5, 1, 9, 4}, 1, 2));
        System.out.println(maxSumTwoNoOverlap(new int[]{3, 8, 1, 3, 2, 1, 8, 9, 0}, 3, 2));
        System.out.println(maxSumTwoNoOverlap(new int[]{2, 1, 5, 6, 0, 9, 5, 0, 3, 8}, 4, 3));
        System.out.println(maxSumTwoNoOverlap(new int[]{1, 0, 1}, 1, 1));
        System.out.println(maxSumTwoNoOverlap(new int[]{1, 0, 3}, 1, 2));
    }


    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;

        int[] sum = new int[n + 1];

        //计算前缀和
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }


        int max = 0;

        for (int i = firstLen; i <= n; i++) {
            int secondMax = 0;

            int firstMax = sum[i] - sum[i - firstLen];

            for (int j = secondLen; j <= i - firstLen; j++) {
                secondMax = Math.max(secondMax, sum[j] - sum[j - secondLen]);
            }

            for (int j = i + 1; j <= n - secondLen; j++) {
                secondMax = Math.max(secondMax, sum[j + secondLen] - sum[j]);
            }

            max = Math.max(max, firstMax + secondMax);
        }

        return max;
    }


}
