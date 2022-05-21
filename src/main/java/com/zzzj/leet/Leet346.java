package com.zzzj.leet;


/**
 * @author zzzj
 * @create 2022-05-11 17:40
 */
public class Leet346 {

    static class MovingAverage {

        private final int windowSize;

        private double sum;

        private int[] queue;

        private int index;

        private int size;

        public MovingAverage(int size) {
            this.windowSize = size;
            this.queue = new int[size];
        }

        public double next(int val) {
            if (size == windowSize) {
                sum -= queue[index % windowSize];
                size--;
            }

            queue[index % windowSize] = val;
            sum += val;
            index++;
            size++;

            return sum / size;
        }

    }


}
