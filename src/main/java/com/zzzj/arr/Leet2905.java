package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-01-16 17:32
 */
public class Leet2905 {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int trap(int[] height) {

        int N = height.length;

        if (N == 0) {
            return 0;
        }

        int[] left = new int[N];

        int[] right = new int[N];

        left[0] = height[0];

        for (int i = 1; i < N; i++) {
            left[i] = Math.max(height[i], left[i - 1]);
        }

        right[N - 1] = height[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i + 1]);
        }

        int ans = 0;

        for (int i = 1; i < N - 1; i++) {
            ans += Math.max(0, Math.min(left[i], right[i]) - height[i]);
        }

        return ans;
    }


}
