package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-08-02 17:50
 */
public class Leet477 {

    public static void main(String[] args) {
        System.out.println(totalHammingDistance(new int[]{4, 14, 4}));
        System.out.println(totalHammingDistance(new int[]{4, 14, 2}));
    }

    public static int totalHammingDistance(int[] nums) {
        int[] bits = new int[32];

        final int N = nums.length;

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            for (int j = 0; j < 31; j++) {
                if ((((num >> j) & 1) == 1)) {
                    bits[j]++;
                }
            }
        }

        int ans = 0;

        for (int bit : bits) {
            if (bit > 0 && bit != N) {
                ans += bit * (N - bit);
            }
        }

        return ans;
    }

}
