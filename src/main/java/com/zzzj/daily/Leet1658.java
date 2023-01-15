package com.zzzj.daily;

public class Leet1658 {

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 1, 4, 2, 3}, 5));

        System.out.println(minOperations(new int[]{3, 2, 20, 1, 1, 3}, 10));
    }

    public static int minOperations(int[] nums, int x) {

        int N = nums.length;

        int L = 0;

        int R = N;

        int sum = 0;

        int ans = Integer.MAX_VALUE;

        while (L < N && sum < x) {
            sum += nums[L];
            L++;
        }

        if (sum < x) {
            return -1;
        }

        L -= 1;

        while (L >= 0 || sum < x) {
            if (sum >= x) {
                if (sum == x) {
                    ans = Math.min(ans, L + 1 + (N - R));
                }
                sum -= nums[L];
                L--;
            } else {
                R--;
                sum += nums[R];
            }
        }

        if (sum == x) {
            ans = Math.min(ans, L + 1 + (N - R));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
