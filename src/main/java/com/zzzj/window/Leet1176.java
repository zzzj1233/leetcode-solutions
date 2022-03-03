package com.zzzj.window;

/**
 * @author Zzzj
 * @create 2022-02-19 22:33
 */
public class Leet1176 {

    public static void main(String[] args) {
        System.out.println(dietPlanPerformance(new int[]{1, 2, 3, 4, 5}, 2, 3, 3));

        System.out.println(dietPlanPerformance(new int[]{3, 2}, 2, 0, 1));

        System.out.println(dietPlanPerformance(new int[]{6, 5, 0, 0}, 2, 1, 5));
    }

    public static int dietPlanPerformance(int[] calories, int k, int lower, int upper) {

        int curSum = 0;

        int l = 0;
        int r = k;

        for (int i = 0; i < r; i++) {
            curSum += calories[i];
        }

        int ans = curSum > upper ? 1 : (curSum < lower ? -1 : 0);

        while (r < calories.length) {

            curSum -= calories[l];
            curSum += calories[r];

            ans += curSum > upper ? 1 : (curSum < lower ? -1 : 0);

            l++;
            r++;
        }

        return ans;
    }


}
