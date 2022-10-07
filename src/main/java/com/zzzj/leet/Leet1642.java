package com.zzzj.leet;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-10-07 19:53
 */
public class Leet1642 {

    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1));
    }

    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int N = heights.length;

        int i = 1;

        long bk = bricks;

        for (; i < N; i++) {
            if (heights[i] <= heights[i - 1]) {
                continue;
            } else {
                int dis = heights[i] - heights[i - 1];

                // 优先用砖头,如果砖头不够,用梯子换砖头
                if (bk < dis) {
                    if (ladders == 0) {
                        break;
                    }
                    ladders--;
                    if (!queue.isEmpty() && queue.peek() > dis) {
                        bk += queue.remove() - dis;
                        queue.add(dis);
                    }
                } else {
                    queue.add(dis);
                    bk -= dis;
                }
            }
        }

        return i - 1;
    }

}
