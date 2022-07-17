package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-07-09 11:43
 */
public class Leet871 {

    public static void main(String[] args) {
        // 100
        // 25
        // [[25,25],[50,25],[75,25]]
        System.out.println(minRefuelStops(100, 25, LeetUtils.convertInts("[[25,25],[50,25],[75,25]]")));
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {

        int cur = startFuel;


        PriorityQueue<Integer> queue = new PriorityQueue<>(Math.max(1, stations.length), (o1, o2) -> o2 - o1);

        int N = stations.length;

        int ans = 0;

        for (int i = 0; i < N && cur < target; i++) {

            // 需要加油
            if (cur < stations[i][0]) {
                while (cur < stations[i][0] && !queue.isEmpty()) {
                    cur += queue.remove();
                    ans++;
                }

                if (cur < stations[i][0]) {
                    return -1;
                }

            }

            queue.add(stations[i][1]);
        }

        while (cur < target && !queue.isEmpty()) {
            cur += queue.remove();
            ans++;
        }

        if (cur < target) {
            return -1;
        }

        return ans;
    }

}
