package com.zzzj.contest.dweek144;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-11-23 22:37
 */
public class Q3 {

    public static void main(String[] args) {

        System.out.println(maxRemoval(new int[]{2, 0, 2}, LeetUtils.convertInts("[[0,2],[0,2],[1,1]]")));

        System.out.println(maxRemoval(new int[]{1, 1, 1, 1}, LeetUtils.convertInts("[[1,3],[0,2],[1,3],[1,2]]")));

        System.out.println(maxRemoval(new int[]{1, 2, 3, 4}, LeetUtils.convertInts("[[0,3]]")));

    }

    public static int maxRemoval(int[] nums, int[][] queries) {

        int N = nums.length;

        int M = queries.length;

        Arrays.sort(queries, (o1, o2) -> {
            int diff = o1[0] - o2[0];
            if (diff == 0)
                return o1[1] - o2[1];
            return diff;
        });

        int cur = 0;

        int[] diff = new int[N + 1];

        int j = 0;

        int cnt = 0;

        for (int i = 0; i < N; i++) {

            if (nums[i] == 0)
                continue;

            cur += diff[i];

            if (cur < nums[i]) {

                if (j >= M || queries[j][0] > i)
                    return -1;

                while (j + 1 < M && queries[j + 1][0] <= i)
                    j++;

                cur += 1;

                diff[queries[j][1] + 1]--;

                j++;

                cnt++;
            }

        }

        return M - cnt;
    }


}
