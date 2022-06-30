package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-01-16 21:54
 */
public class Leet11 {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static int maxArea(int[] height) {

        // 求每个杆的最优解
        int ans = 0;

        int l = 0;
        int r = height.length - 1;

        while (l < r) {

            // 求height[l]的最优解
            if (height[l] < height[r]) {
                ans = Math.max(ans, height[l] * (r - l));
                l++;
                // 求height[r]的最优解
            } else {
                ans = Math.max(ans, height[r] * (r - l));
                r--;
            }

        }

        return ans;
    }

}
