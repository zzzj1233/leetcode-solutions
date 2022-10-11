package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-10-11 16:04
 */
public class Leet2348 {

    public static void main(String[] args) {
        System.out.println(zeroFilledSubarray(new int[]{0, 0, 0, 2, 0, 0}));
    }

    public static long zeroFilledSubarray(int[] nums) {

        long ans = 0;

        int N = nums.length;

        int L = 0;

        while (L < N && nums[L] != 0) {
            L++;
        }

        int R = L;

        while (R < N) {

            if (nums[R] != 0) {
                int c = R - L;
                ans += (long) (c + 1) * c / 2;
                L = R + 1;
            }
            R++;
        }

        if (L != R) {
            int c = R - L;
            ans += (long) (c + 1) * c / 2;
        }

        return ans;
    }

}
