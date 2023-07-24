package com.zzzj.contest.week352;

/**
 * @author zzzj
 * @create 2023-07-18 12:05
 */
public class Leet2760 {

    public static void main(String[] args) {

        System.out.println(longestAlternatingSubarray(new int[]{3, 2, 5, 4}, 5));

        System.out.println(longestAlternatingSubarray(new int[]{1, 2}, 2));

        System.out.println(longestAlternatingSubarray(new int[]{2, 3, 4, 5}, 4));

        System.out.println(longestAlternatingSubarray(new int[]{2, 8}, 4));

        System.out.println(longestAlternatingSubarray(new int[]{2, 2}, 4));

    }

    public static int longestAlternatingSubarray(int[] nums, int threshold) {

        int N = nums.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            if (nums[i] % 2 != 0 || nums[i] > threshold) continue;

            int cur = 1;

            for (int j = i + 1; j < N; j++) {

                if (nums[j] > threshold) break;

                if (nums[j] % 2 == nums[j - 1] % 2) break;

                cur++;
            }

            ans = Math.max(ans, cur);
        }

        return ans;
    }

}
