package com.zzzj.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-04-01 10:42
 */
public class Leet973 {

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(points.length, (o1, o2) -> {
            int x1 = o1[0];
            int y1 = o1[1];

            int x2 = o2[0];
            int y2 = o2[1];

            double c1 = Math.sqrt(x1 * x1 + y1 * y1);
            double c2 = Math.sqrt(x2 * x2 + y2 * y2);

            return Double.compare(c1, c2);
        });

        queue.addAll(Arrays.asList(points));

        int[][] ans = new int[queue.size()][];

        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            ans[i] = queue.remove();
        }

        return ans;
    }

}
