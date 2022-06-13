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
            if (height[l] < height[r]) {
                ans = Math.max(ans, height[l] * (r - l + 1));
                l++;
            } else { // 可以结算右边的结果
                ans = Math.max(ans, height[r] * (r - l + 1));
                r--;
            }
        }

        return ans;
    }

}
