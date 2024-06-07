package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2024-06-07 16:46
 */
public class Leet2386 {

    public static long kSum(int[] nums, int k) {

        int N = nums.length;

        long l = 0;

        long r = 0;

        int[] neg = new int[N + 1];

        for (int num : nums) {
            if (num < 0)
                l += num;
            else
                r += num;
        }

        

        l--;

        while (l + 1 < r) {

            long m = l + ((r - l) >> 1);

            if (check(nums, m, k))
                r = m;
            else
                l = m;

        }

        return r;
    }

    public static boolean check(
            int[] nums,
            long exp,
            int k
    ) {

        int N = nums.length;

        int l = 0;

        int r = 0;

        long s = 0;

        long cnt = 0;

        while (r < N) {

            s += nums[r];

            while (s > r && l <= r) {
                s -= nums[l];
                l++;
            }

            if (s < r) {

            }

        }

        return false;
    }

}
