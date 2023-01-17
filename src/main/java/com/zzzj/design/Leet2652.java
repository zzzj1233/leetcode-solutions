package com.zzzj.design;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-01-16 17:57
 */
public class Leet2652 {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(-1);
        finder.addNum(-2);
//        System.out.println(finder.findMedian());
        finder.addNum(-3);
        finder.addNum(-4);
        finder.addNum(-5);
        System.out.println(finder.findMedian());
    }

    /**
     * 永远维持:
     * 小根堆最小的元素 >= 大根堆最大的元素
     */
    private static class MedianFinder {

        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        public MedianFinder() {

        }

        public void addNum(int num) {
            // 1. 来一个数据,不知道大小
            maxHeap.add(num);
            // 但是小根堆[最小]的数据,必须大于大根堆[最大]的数据
            minHeap.add(maxHeap.remove());

            // 2. 如果两个队列长度失衡, 那么把小根堆[最小]的元素给大根堆
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.remove());
            }
        }

        public double findMedian() {
            if (minHeap.size() == maxHeap.size()) {
                return (((double) (maxHeap.peek())) + minHeap.peek()) / 2.0;
            }

            return maxHeap.peek();
        }

    }
}
