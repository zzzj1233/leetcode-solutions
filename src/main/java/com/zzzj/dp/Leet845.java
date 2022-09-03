package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-30 17:54
 */
public class Leet845 {

    public static void main(String[] args) {
        System.out.println(longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(longestMountain(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    public static int longestMountain(int[] arr) {
        int N = arr.length;

        if (N < 3) {
            return 0;
        }

        int[] left = new int[N];
        int[] right = new int[N];

        for (int i = 1; i < N; i++) {
            if (arr[i - 1] < arr[i]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            if (arr[i + 1] < arr[i]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (left[i] >= 1 && right[i] >= 1) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }

        return ans;
    }

}
