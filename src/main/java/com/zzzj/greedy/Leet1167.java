package com.zzzj.greedy;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-09-07 19:54
 */
public class Leet1167 {

    public static int connectSticks(int[] sticks) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int stick : sticks) {
            queue.add(stick);
        }

        int ans = 0;

        while (queue.size() > 1) {
            Integer min1 = queue.poll();
            Integer min2 = queue.poll();
            queue.add(min1 + min2);
            ans += min1 + min2;
        }

        return ans;
    }

}
