package com.zzzj.bit;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-19 15:22
 */
public class Leet2354 {

    public static void main(String[] args) {

        System.out.println(countExcellentPairs(new int[]{1, 2, 3, 1}, 3));

        System.out.println(countExcellentPairs(new int[]{5, 1, 1}, 10));

    }

    public static long countExcellentPairs(int[] nums, int k) {

        nums = Arrays.stream(nums).distinct().toArray();

        int N = nums.length;

        int[] bc = new int[N];

        for (int i = 0; i < N; i++) bc[i] = Integer.bitCount(nums[i]);

        Arrays.sort(bc);

        long ans = 0;

        for (int i = 0; i < N; i++) {

            int cnt = bc[i];

            int expect = k - cnt;

            ans += search(bc, i - 1, expect);

            if (cnt << 1 >= k)
                ans++;

        }

        return ans;
    }

    public static long search(
            int[] bc,
            int R,
            int expect
    ) {
        int left = 0;
        int right = R;

        long res = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (bc[mid] >= expect) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return (res + 1) << 1;
    }


}
