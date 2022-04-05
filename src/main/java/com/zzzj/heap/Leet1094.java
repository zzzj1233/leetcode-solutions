package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-03-13 11:14
 */
public class Leet1094 {


    public static void main(String[] args) {
//        System.out.println(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
//        System.out.println(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5));
//        System.out.println(carPooling(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3));

//        System.out.println(carPooling(LeetUtils.convertInts("[[1, 1, 2], [3, 3, 6], [3, 1, 3]]"), 5));
//
//        System.exit(0);

        int random = 40;

        for (int k = 0; k < 10000; k++) {
            int n = LeetUtils.random.nextInt(random);

            int[][] trips = new int[n][3];

            for (int i = 0; i < n; i++) {
                int start = LeetUtils.random.nextInt(random) + 1;
                int end = LeetUtils.random.nextInt(random) + 1 + start;
                trips[i] = new int[]{LeetUtils.random.nextInt(random) + 1, start, end};
            }

            int capacity = LeetUtils.random.nextInt(100) + 1;

            if (carPooling(trips, capacity) != right(trips, capacity)) {
                System.out.println(Arrays.deepToString(trips));
                System.out.println(capacity);
                System.out.println(carPooling(trips, capacity));
                return;
            }
        }

    }

    public static boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, Comparator.comparingInt(o -> ((int[]) o)[1]).thenComparingInt(o -> ((int[]) o)[2]));

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> ((int[]) o)[1]).thenComparingInt(o -> ((int[]) o)[0]));

        for (int i = 0; i < trips.length; i++) {
            queue.add(new int[]{i, trips[i][1]});
            queue.add(new int[]{i, trips[i][2]});
        }

        int curPassenger = 0;

        while (!queue.isEmpty()) {
            int[] it = queue.remove();

            int time = it[1];

            int index = it[0];

            // 开始
            if (time == trips[index][1]) {
                curPassenger += trips[index][0];
                // 结束
            } else {
                curPassenger -= trips[index][0];
            }

            if (curPassenger > capacity) {
                return false;
            }

        }

        return true;
    }

    public static boolean right(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // key：节点 value：每个节点的人数
        for (int[] trip : trips) {
            int passen = trip[0]; // 当前上车人数
            int start = trip[1];  // 上车地点
            int end = trip[2];    // 下车地点
            for (int i = start; i < end; ++i) {
                map.put(i, map.getOrDefault(i, 0) + passen);  // 上车， 将上车人数加到当前节点，之前有人则加上
                if (map.get(i) > capacity) { // 上车之后判断一下是否超载
                    return false;
                }
            }
        }
        return true;
    }


}
