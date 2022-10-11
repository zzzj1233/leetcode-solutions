package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-10-11 11:59
 */
public class Leet2401 {

    public static void main(String[] args) {
        System.out.println(longestNiceSubarray(new int[]{1, 3, 8, 48, 10}));
        System.out.println(longestNiceSubarray(new int[]{3, 1, 5, 11, 13}));
    }

    public static int longestNiceSubarray(int[] nums) {

        int N = nums.length;

        int window = nums[0];

        int L = 0;
        int R = 1;

        int ans = 1;

        while (R < N) {
            int num = nums[R];

            // 缩短窗口
            while ((window & num) > 0) {
                window ^= nums[L];
                L++;
            }

            window |= num;
            ans = Math.max(ans, R - L + 1);
            R++;
        }

        return ans;
    }

}
