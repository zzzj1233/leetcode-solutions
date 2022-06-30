package com.zzzj.leet;

import cn.hutool.core.lang.Assert;
import com.zzzj.util.Unresolved;

/**
 * @author zzzj
 * @create 2022-06-24 17:13
 */
@Unresolved
public class Leet622 {

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test3() {
        // ["MyCircularQueue","enQueue","Rear","Front","deQueue","Front","deQueue","Front","enQueue","enQueue","enQueue","enQueue"]
        // [[3],[2],[],[],[],[],[],[],[4],[2],[2],[3]]

    }

    public static void test1() {
        /**
         ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
         [[3],[1],[2],[3],[4],[],[],[],[4],[]]
         预期结果
         [null,true,true,true,false,3,true,true,true,4]
         */
        MyCircularQueue queue = new MyCircularQueue(3);
        Assert.isTrue(queue.enQueue(1));
        Assert.isTrue(queue.enQueue(2));
        Assert.isTrue(queue.enQueue(3));
        Assert.isFalse(queue.enQueue(4));
        Assert.isTrue(queue.Rear() == 3);
        Assert.isTrue(queue.isFull());
        Assert.isTrue(queue.deQueue());
        Assert.isTrue(queue.enQueue(4));
        Assert.isTrue(queue.Rear() == 4);
    }

    public static void test2() {
        MyCircularQueue queue = new MyCircularQueue(8);

        // [null,true,true,true,true,true,true,false,false,0,0,true]

        // ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
        // [[3],[1],[2],[3],[4],[],[],[],[4],[]]

        Assert.isTrue(queue.enQueue(3));
        Assert.isTrue(queue.enQueue(9));
        Assert.isTrue(queue.enQueue(5));
        Assert.isTrue(queue.enQueue(0));
        Assert.isTrue(queue.deQueue());
        Assert.isTrue(queue.deQueue());
        Assert.isFalse(queue.isEmpty());
        Assert.isFalse(queue.isEmpty());

        Assert.isTrue(queue.Rear() == 0);
        Assert.isTrue(queue.Rear() == 0);
        Assert.isTrue(queue.deQueue());
    }

    private static class MyCircularQueue {

        int[] arr;

        private int size;

        // 头指针
        int frontIndex;

        // 尾指针
        int tailIndex;

        boolean deQueued;

        public MyCircularQueue(int k) {
            arr = new int[k];
        }

        // 入队
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            arr[tailIndex] = value;
            tailIndex++;
            size++;
            if (tailIndex >= arr.length) {
                tailIndex %= arr.length;
            }
            return true;
        }

        // 出队第一个元素
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            deQueued = true;
            frontIndex++;
            if (frontIndex >= arr.length) {
                frontIndex %= arr.length;
            }
            size--;
            return true;
        }

        // 第一个元素
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            if (!deQueued) {
                return arr[0];
            }
            if (frontIndex - 1 < 0) {
                return arr[arr.length - 1];
            }
            return arr[frontIndex - 1];
        }

        // 最后一个元素
        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            if (tailIndex - 1 < 0) {
                return arr[arr.length - 1];
            }
            return arr[tailIndex - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == arr.length;
        }

    }

}
