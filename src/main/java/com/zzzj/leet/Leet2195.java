package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-30 16:36
 */
public class Leet2195 {

    public static void main(String[] args) {
        System.out.println(minimalKSum(new int[]{96, 44, 99, 25, 61, 84, 88, 18, 19, 33, 60, 86, 52, 19, 32, 47, 35, 50, 94, 17, 29, 98, 22, 21, 72, 100, 40, 84}, 35));
    }

    // [96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84]
    // 35
    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);

        long ans = 0;

        if (k > 0 && nums[0] > 1) {
            int min = 1;
            int max = Math.min(nums[0] - 1, k);
            ans += sum(min, max);
            k -= max - min + 1;
        }

        if (k <= 0) {
            return ans;
        }

        for (int i = 1; i < nums.length; i++) {
            int sub = nums[i] - nums[i - 1];

            if (sub <= 1) {
                continue;
            }

            int min = nums[i - 1] + 1;
            int max = Math.min(nums[i] - 1, min + k - 1);

            ans += sum(min, max);

            k -= max - min + 1;

            if (k == 0) {
                return ans;
            }
        }


        if (k > 0) {
            int min = nums[nums.length - 1] + 1;
            int max = min + k - 1;
            ans += sum(min, max);
        }

        return ans;
    }

    public static long sum(int x, int y) {
        return (long) ((x + y) * ((double) (y - x + 1) / 2));
    }

}
