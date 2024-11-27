package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2024-11-24 18:58
 */
public enum Leet3356 {
    ;

    public static void main(String[] args) {

        System.out.println(minZeroArray(new int[]{2, 0, 2}, LeetUtils.convertInts("[[0,2,1],[0,2,1],[1,1,3]]")));

        System.out.println(minZeroArray(new int[]{4, 3, 2, 1}, LeetUtils.convertInts("[[1,3,2],[0,2,1]]")));

    }

    public static int minZeroArray(int[] nums, int[][] queries) {

        int l = 0;
        int r = queries.length;
        int ans = -1;

        while (l <= r) {

            int m = l + ((r - l) >> 1);

            if (can(m, nums, queries)) {
                ans = m;
                r = m - 1;
            } else
                l = m + 1;
        }

        return ans;
    }

    public static boolean can(
            int k,
            int[] nums,
            int[][] queries
    ) {

        int[] diff = new int[nums.length + 1];

        for (int i = 0; i < k; i++) {
            diff[queries[i][0]] += queries[i][2];
            diff[queries[i][1] + 1] -= queries[i][2];
        }

        int cur = 0;

        for (int i = 0; i < nums.length; i++) {
            cur += diff[i];

            if (cur < nums[i])
                return false;

        }

        return true;
    }

}
