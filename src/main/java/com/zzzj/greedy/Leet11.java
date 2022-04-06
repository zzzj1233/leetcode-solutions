package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-03-30 16:44
 */
public class Leet11 {

    public static int maxArea(int[] height) {

        int l = 0;
        int r = height.length - 1;

        int ans = 0;

        while (l < r) {
            int right = height[r];
            int left = height[l];
            ans = Math.max(ans, Math.min(right, left) * (r - l + 1));
            if (left < right) {
                l++;
            } else {
                r++;
            }
        }

        return ans;
    }

}
