package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-26 17:25
 */
public class Leet2110 {

    public static void main(String[] args) {
        // (n + 1) * n / 2
        System.out.println(getDescentPeriods(new int[]{4, 3, 2, 1, 4}));
    }

    public static long getDescentPeriods(int[] prices) {

        int N = prices.length;

        long ans = 1;

        int left = 0;

        for (int i = 1; i < N; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                ans += i - left + 1;
            } else {
                left = i;
                ans++;
            }
        }

        return ans;
    }

}
