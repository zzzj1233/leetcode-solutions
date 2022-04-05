package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-03 19:30
 */
public class Leet42 {

    public static int trap(int[] height) {
        int water = 0;

        int L = 1;
        int R = height.length - 2;

        int maxL = height[0];
        int maxR = height[height.length - 1];

        while (L <= R) {
            if (maxL < maxR) {
                maxL = Math.max(maxL, height[L]);
                water += Math.max(0, maxL - height[L]);
                L++;
            } else {
                maxR = Math.max(maxR, height[R]);
                water += Math.max(0, maxR - height[R]);
                R--;
            }
        }

        return water;
    }

}
