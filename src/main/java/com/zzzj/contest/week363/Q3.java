package com.zzzj.contest.week363;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.List;

public class Q3 {

    public static void main(String[] args) {
//
        System.out.println(maxNumberOfAlloys(3, 2, 15, LeetUtils.convertLists("[[1,1,1],[1,1,10]]"), Arrays.asList(0, 0, 0), Arrays.asList(1, 2, 3)));

        System.out.println(maxNumberOfAlloys(3, 2, 15, LeetUtils.convertLists("[[1,1,1],[1,1,10]]"), Arrays.asList(0, 0, 100), Arrays.asList(1, 2, 3)));

        System.out.println(maxNumberOfAlloys(2, 3, 10, LeetUtils.convertLists("[[2,1],[1,2],[1,1]]"), Arrays.asList(1, 1), Arrays.asList(5, 5)));

    }

    public static int maxNumberOfAlloys(int n, int machine, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {

        int ans = 0;

        // k台机器

        for (int i = 0; i < machine; i++) {

            List<Integer> list = composition.get(i);

            // 二分
            int left = 0;
            int right = Integer.MAX_VALUE;

            int cnt = 0;

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (can(stock, cost, budget, list, mid)) {
                    cnt = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            ans = Math.max(ans, cnt);

        }

        return ans;
    }

    public static boolean can(List<Integer> stock, List<Integer> cost, long budget, List<Integer> list, int expect) {

        for (int i = 0; i < list.size() && budget >= 0; i++) {
            long need = (long) list.get(i) * expect;
            if (stock.get(i) >= need)
                continue;
            // budget是否够
            need -= stock.get(i);
            budget -= cost.get(i) * need;
        }

        return budget >= 0;
    }
}
