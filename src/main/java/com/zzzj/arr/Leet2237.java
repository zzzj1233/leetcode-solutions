package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

public class Leet2237 {

    public static void main(String[] args) {
        System.out.println(meetRequirement(5, LeetUtils.convertInts("[[0,1],[2,1],[3,2]]"), null));
    }

    public static int meetRequirement(int n, int[][] lights, int[] requirement) {
        int[] preSum = new int[n];

        for (int[] light : lights) {
            int p = light[0];
            int r = light[1];

            preSum[Math.max(0, p - r)] += 1;

            if (p + r + 1 < n) {
                preSum[p + r + 1] -= 1;
            }
        }

        for (int i = 1; i < n; i++) {
            preSum[i] += preSum[i - 1];
        }

        int ans = 0;

        for (int i = 0; i < requirement.length; i++) {
            if (preSum[i] >= requirement[i]) {
                ans++;
            }
        }

        return ans;
    }

}
