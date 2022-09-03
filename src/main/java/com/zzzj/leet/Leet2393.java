package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-02 15:03
 */
public class Leet2393 {

    public static long countSubarrays(int[] nums) {
        int N = nums.length;

        if (N == 0) {
            return 0;
        }

        int left = 0;
        long ans = 1;

        for (int i = 1; i < N; i++) {
            if (nums[i] > nums[i - 1]) {
                ans += i - left;
            } else {
                left = i;
            }
            ans++;
        }

        return ans;
    }


}
