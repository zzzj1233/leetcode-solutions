package com.zzzj.daily;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zzzj
 * @create 2023-07-07 16:07
 */
public class Leet2532 {


    public static final int LEFT_TO_RIGHT = 0;

    public static final int PICK_OLD = 1;

    public static final int RIGHT_TO_LEFT = 2;

    public static final int PUT_NEW = 3;

    //[[1,1,2,1],[1,1,3,1],[1,1,4,1]]
    //[[5,5,5,5],[1,1,1,1],[2,2,2,2]]
        public static int findCrossingTime(int n, int k, int[][] time) {

        Comparator<Integer> comparator = (o1, o2) -> {
            int diff = (time[o1][LEFT_TO_RIGHT] + time[o1][RIGHT_TO_LEFT]) -
                    (time[o2][LEFT_TO_RIGHT] + time[o2][RIGHT_TO_LEFT]);
            return diff == 0 ? o2 - o1 : diff;
        };

        Queue<Integer> leftQueue = new PriorityQueue<>(k, comparator);

        Queue<Integer> rightQueue = new PriorityQueue<>(k, comparator);

        for (int i = 0; i < k; i++) leftQueue.add(i);

        int ans = 0;

        // 1. 右边有人必须右边先走, 并且效率最低先走
        // 2. 右边没人, 左边才能走, 并且效率最低先走

        while (k > 0) {

            if (rightQueue.isEmpty()) {

                Integer lr = leftQueue.remove();

                ans += time[lr][LEFT_TO_RIGHT];

                rightQueue.add(lr);

                // 为什么LR不能直接搬箱子再返回到左边?
                // 因为有可能桥左边还有人
                // leftToRight + pickOld + rightToLeft + putNew? > lr: pickOld + rightToLeft + putNew?

                // 如果是最后一个箱子, putNew的时间可以忽略

            }

        }

        return -1;
    }

}
