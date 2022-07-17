package com.zzzj.greedy;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author Zzzj
 * @create 2022-07-10 16:24
 */
public class Leet826 {

    public static void main(String[] args) {
        System.out.println(maxProfitAssignment(new int[]{68, 35, 52, 47, 86}, new int[]{67, 17, 1, 81, 3}, new int[]{92, 10, 85, 84, 82}));
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        int ans = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(difficulty.length, (o1, o2) -> {
            if (difficulty[o1] == difficulty[o2]) {
                return profit[o2] - profit[o1];
            }
            return difficulty[o1] - difficulty[o2];
        });

        int N = difficulty.length;

        for (int i = 0; i < N; i++) {
            queue.add(i);
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();

        int preProfit = -1;

        while (!queue.isEmpty()) {
            Integer index = queue.remove();

            if (profit[index] < preProfit) {
                continue;
            }

            preProfit = profit[index];

            map.put(difficulty[index], preProfit);
        }

        for (int i = 0; i < worker.length; i++) {
            int ability = worker[i];
            Map.Entry<Integer, Integer> work = map.floorEntry(ability);
            if (work != null) {
                ans += work.getValue();
            }
        }

        return ans;
    }


}
