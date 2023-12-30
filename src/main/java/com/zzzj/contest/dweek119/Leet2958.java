package com.zzzj.contest.dweek119;

import java.util.HashMap;
import java.util.Map;

public class Leet2958 {

    public static void main(String[] args) {

        System.out.println(maxSubarrayLength(new int[]{2, 2, 2, 3}, 1));

        System.out.println(maxSubarrayLength(new int[]{1, 2, 3, 1, 2, 3, 1, 2}, 2));

        System.out.println(maxSubarrayLength(new int[]{1, 2, 1, 2, 1, 2, 1, 2}, 1));

        System.out.println(maxSubarrayLength(new int[]{5, 5, 5, 5, 5, 5, 5}, 4));

    }

    public static int maxSubarrayLength(int[] nums, int k) {

        int N = nums.length;

        Map<Integer, Integer> rec = new HashMap<>(N);

        int left = 0;

        int right = 0;

        int ans = 0;

        while (right < N) {

            int num = nums[right];

            int cnt = rec.getOrDefault(num, 0) + 1;

            rec.put(num, cnt);

            while (cnt > k && left < right) {

                int n1 = nums[left];

                if (n1 == num) {
                    cnt--;
                }

                rec.put(n1, rec.get(n1) - 1);

                left++;
            }

            right++;

            ans = Math.max(ans, right - left);
        }

        return ans;
    }

}
