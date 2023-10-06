package com.zzzj.leet;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-10-06 20:07
 */
public class Leet871 {

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int fuel = startFuel;

        int location = 0;

        int ans = 0;

        for (int[] station : stations) {

            int distance = station[0];

            int cost = distance - location;

            while (true) {
                if (fuel >= cost) {
                    fuel -= cost;
                    break;
                }
                if (queue.isEmpty())
                    return -1;
                fuel += queue.remove();
                ans++;
            }
            location = distance;
            queue.add(station[1]);
        }

        int cost = target - location;

        while (true) {
            if (fuel >= cost)
                break;
            if (queue.isEmpty())
                return -1;
            fuel += queue.remove();
            ans++;
        }

        return ans;
    }

}
