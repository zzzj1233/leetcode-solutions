package com.zzzj.interview.solutions.lesson4;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Zzzj
 * @create 2022-07-02 16:32
 */
public class Question1Solution {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int N = LeetUtils.random.nextInt(100);
            int[][] job = LeetUtils.randomMatrix(N, 2, 1, 1000);
            int[] ability = ArrayUtil.generateArray(N, 1, 1000);
            if (violent(job, ability) != maxProfit(job, ability)) {
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int violent(int[][] job, int[] ability) {
        int ans = 0;

        for (int ab : ability) {
            int maxProfit = 0;
            for (int[] ints : job) {
                if (ab >= ints[1]) {
                    maxProfit = Math.max(maxProfit, ints[0]);
                }
            }
            ans += maxProfit;
        }
        return ans;
    }

    public static int maxProfit(int[][] job, int[] ability) {
        // 1. 按照能力排序,从小到大
        // 2. 按照收益排序,从大到小
        List<int[]> jobList = Arrays.stream(job)
                .sorted((jb1, jb2) -> {
                    // 难度相同
                    if (jb1[1] == jb2[1]) {
                        return jb2[0] - jb1[0];
                    }
                    return jb1[1] - jb2[1];
                }).collect(Collectors.toList());


        TreeMap<Integer, Integer> jobMap = new TreeMap<>();

        int prevHard = -1;
        int prevProfit = -1;

        for (int[] ints : jobList) {
            int hard = ints[1];
            if (hard == prevHard) {
                continue;
            }
            prevHard = hard;

            int profit = ints[0];
            if (profit < prevProfit) {
                continue;
            }
            prevProfit = profit;
            jobMap.put(hard, profit);
        }

        int ans = 0;

        for (int ab : ability) {
            Integer key = jobMap.floorKey(ab);
            if (key != null) {
                ans += jobMap.get(key);
            }
        }

        return ans;
    }

}
