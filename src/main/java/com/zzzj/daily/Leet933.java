package com.zzzj.daily;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-05-06 15:01
 */
public class Leet933 {

    static class RecentCounter {

        LinkedList<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            int deadLine = t - 3000;

            while (!queue.isEmpty() && queue.peekFirst() < deadLine) {
                queue.removeFirst();
            }

            queue.add(t);

            return queue.size();
        }

    }


}
