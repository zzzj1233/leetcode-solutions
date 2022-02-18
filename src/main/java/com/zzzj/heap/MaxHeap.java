package com.zzzj.heap;

import com.zzzj.util.ArrayUtil;

/**
 * @author Zzzj
 * @create 2021-04-17 16:15
 */
public class MaxHeap {

    private int[] data;

    private int count;

    // 特性:
    // 所有的父节点的值都大于他们的子节点
    public MaxHeap(int capacity) {
        data = new int[capacity + 1];
        this.count = 0;
    }

    // 思想:
    // 对所有的非叶子节点进行一次shiftDown
    public static MaxHeap heapify(int[] data) {
        MaxHeap maxHeap = new MaxHeap(data.length);
        maxHeap.count = data.length;
        for (int i = 0; i < data.length; i++) {
            // 索引从1开始
            maxHeap.data[i + 1] = data[i];
        }

        // heapify ~
        // maxHeap的第一个非叶子节点的索引一定是count / 2
        int rootIdx = maxHeap.count / 2;

        for (int i = rootIdx; i >= 1; i--) {
            maxHeap.shiftDown(i);
        }

        return maxHeap;
    }

    public void insert(int value) {
        this.data[count + 1] = value;
        shiftUp(count + 1);
        this.count++;
    }

    public int popMax() {
        int max = this.data[1];
        this.data[1] = this.data[count];
        this.data[count] = 0;
        this.count--;
        shiftDown(1);
        return max;
    }

    private void shiftDown(int idx) {
        int leftIdx = getLeftChild(idx);
        int rightIdx = getRightChild(idx);

        if (leftIdx > this.count) {
            return;
        }

        int left = this.data[leftIdx];
        int right = rightIdx > this.count ? 0 : this.data[rightIdx];

        if (left > this.data[idx] && left > right) {
            ArrayUtil.swap(this.data, leftIdx, idx);
            shiftDown(leftIdx);
        } else if (right > this.data[idx]) {
            ArrayUtil.swap(this.data, rightIdx, idx);
            shiftDown(rightIdx);
        }
    }

    private void shiftUp(int idx) {
        int parent = getParent(idx);

        if (parent == 0) {
            return;
        }

        if (this.data[idx] > this.data[parent]) {
            ArrayUtil.swap(this.data, idx, parent);
            shiftUp(parent);
        }

    }

    private int getParent(int idx) {
        return idx / 2;
    }

    private int getLeftChild(int idx) {
        return idx << 1;
    }

    private int getRightChild(int idx) {
        return (idx << 1) + 1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void testMaxHeap() {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(2);
        maxHeap.insert(1);
        maxHeap.insert(5);
        maxHeap.insert(4);
        maxHeap.insert(3);
        maxHeap.insert(999);

        System.out.println(maxHeap.popMax());
        System.out.println(maxHeap.popMax());
        System.out.println(maxHeap.popMax());
        System.out.println(maxHeap.popMax());
    }


    public static void heapSort(int[] arr) {
        MaxHeap maxHeap = MaxHeap.heapify(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.popMax();
        }

    }

    public static void main(String[] args) {
        // testMaxHeap();

        int[] arr1 = ArrayUtil.generateArray(1000000);

        // 141ms 和 quickSort基本持平
        ArrayUtil.testSort(arr1, MaxHeap::heapSort);
    }

}
