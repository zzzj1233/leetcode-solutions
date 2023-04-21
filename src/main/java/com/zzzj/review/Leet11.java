package com.zzzj.review;

/**
 * @author zzzj
 * @create 2023-03-09 15:25
 */
public class Leet11 {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static int maxArea(int[] height) {

        int N = height.length;

        int left = 0;

        int right = N - 1;

        int ans = 0;

        while (left < right) {

            int h = Math.min(height[left], height[right]);

            ans = Math.max(ans, h * (right - left));

            if (h == height[left]) {
                left++;
            } else {
                right--;
            }

        }

        return ans;
    }

}
