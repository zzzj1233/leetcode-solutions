package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-01-28 18:07
 */
public class Leet1589 {

    public static void main(String[] args) {
        System.out.println(maxSumRangeQuery(
                new int[]{1, 2, 3, 4, 5, 10},
                LeetUtils.convertInts("[[0,2],[1,3],[1,1]]")
        ));

        System.out.println(maxSumRangeQuery(
                new int[]{1, 2, 3, 4, 5},
                LeetUtils.convertInts("[[1,3],[0,1]]")
        ));

        System.out.println(maxSumRangeQuery(
                new int[]{1, 8, 5, 5, 2},
                LeetUtils.convertInts("[[4,4],[3,4],[4,4],[2,4],[0,0]]")
        ));

        System.out.println(maxSumRangeQuery(
                new int[]{4, 5, 1},
                LeetUtils.convertInts("[[0,1],[0,2],[1,2]]")
        ));

        System.out.println(maxSumRangeQuery(
                new int[]{1, 2, 3, 4, 5},
                LeetUtils.convertInts("[[1,3],[0,1]]")
        ));

        System.out.println(maxSumRangeQuery(
                new int[]{5, 5, 3},
                LeetUtils.convertInts("[[2,2],[0,1],[1,2]]")
        ));
    }

    public static int maxSumRangeQuery(int[] nums, int[][] requests) {
        int N = nums.length;

        int[] help = new int[N + 1];

        for (int[] request : requests) {
            int s = request[0];
            int e = request[1];
            help[s]++;
            help[e + 1]--;
        }

        Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);

        Arrays.sort(nums);

        int pre = 0;

        int len = 0;

        for (int i = 0; i <= N; i++) {
            if (help[i] != 0) {
                if (len > 0 && pre > 0) {
                    map.put(pre, map.getOrDefault(pre, 0) + len);
                    len = 0;
                }
                pre += help[i];
            }
            if (pre > 0) {
                len++;
            }
        }

        if (len > 0 && pre > 0) {
            map.put(pre, map.getOrDefault(pre, 0) + len);
        }

        int index = N - 1;

        long sum = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                sum += (long) nums[index] * entry.getKey();
                index--;
            }
        }

        final int MOD = 1000000007;

        return (int) (sum % MOD);
    }

}
