package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-05-12 16:34
 */
public class Leet362 {

    static class HitCounter {

        private LinkedList<Integer> queue;

        public HitCounter() {
            queue = new LinkedList<>();
        }

        public void hit(int timestamp) {
            queue.add(timestamp);
            while (!queue.isEmpty() && timestamp - queue.peekFirst() >= 300) {
                queue.removeFirst();
            }
        }

        public int getHits(int timestamp) {
            while (!queue.isEmpty() && timestamp - queue.peekFirst() >= 300) {
                queue.removeFirst();
            }
            return queue.size();
        }

    }

}
