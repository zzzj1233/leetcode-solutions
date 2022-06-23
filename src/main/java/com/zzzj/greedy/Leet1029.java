package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-21 19:17
 */
public class Leet1029 {

    public static void main(String[] args) {
        System.out.println(twoCitySchedCost(LeetUtils.convertInts("[[10,20],[30,200],[400,50],[30,20]]")));
    }

    // 1/2的人在A, 1/2的人在B, 返回最小成本
    public static int twoCitySchedCost(int[][] costs) {
        int N = costs.length;

        Arrays.sort(costs, (o1, o2) -> {
            int o1ACost = o1[0];
            int o1BCost = o1[1];

            int o2ACost = o2[0];
            int o2BCost = o2[1];

            int o1Sub = o1BCost - o1ACost;

            int o2Sub = o2BCost - o2ACost;

            return o2Sub - o1Sub;
        });

        int cost = 0;

        for (int i = 0; i < N / 2; i++) {
            cost += costs[i][0];
        }

        for (int i = N / 2; i < N; i++) {
            cost += costs[i][1];
        }

        return cost;
    }

}
