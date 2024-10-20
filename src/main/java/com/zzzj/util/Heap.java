package com.zzzj.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * @author zzzj
 * @create 2024-10-19 12:59
 */
public class Heap<T, K> {

    private final Comparator<T> comparator;

    private final int N;

    private final Map<K, Integer> indexes;

    private final T[] nodes;

    private final Function<T, K> keyGetter;

    private int size;

    public Heap(Comparator<T> comparator, int N, Function<T, K> keyGetter) {
        this.comparator = comparator;
        this.N = N + 1;
        this.indexes = new HashMap<>();
        this.keyGetter = keyGetter;
        this.size = 0;
        this.nodes = (T[]) new Object[this.N];
    }

    private int parent(int index) {
        return index >> 1;
    }

    private int leftChild(int index) {
        return index << 1;
    }

    private int rightChild(int index) {
        return (index << 1) + 1;
    }

    public void push(T t) {
        // 放到最后, 然后shiftUp
        this.nodes[++this.size] = t;

        K key = keyGetter.apply(t);

        indexes.put(key, this.size);

        shiftUp(size);
    }

    public boolean inHeap(K key) {
        return indexes.containsKey(key);
    }

    public void update(T node) {

        K key = keyGetter.apply(node);

        Integer index = indexes.get(key);

        if (index == null)
            return;

        T old = nodes[index];

        int compared = comparator.compare(old, node);

        if (compared == 0) {
            return;
        }

        // update
        nodes[index] = node;

        // 最小堆
        // 变小了 -> shiftDown
        // 变大了 -> shiftUp
        if (compared < 0)
            shiftUp(index);
        else
            shiftDown(index);

    }

    public void remove(T node) {

        K key = keyGetter.apply(node);

        remove0(key);
    }

    public void remove0(K key) {

        Integer index = indexes.get(key);

        if (index == null)
            return;

        T oldNode = nodes[index];

        swap(index, size);

        size--;

        indexes.remove(key);

        shiftDown(index);
    }

    public T peek() {
        if (size == 0)
            return null;
        return nodes[1];
    }

    public T pop() {
        if (size == 0)
            return null;

        T pop = nodes[1];

        swap(1, size);
        nodes[size] = null;
        size--;
        shiftDown(1);
        indexes.remove(keyGetter.apply(pop));

        return pop;
    }

    private void shiftUp(int index) {

        while (index > 1 && comparator.compare(nodes[index], nodes[parent(index)]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }

    }

    private void shiftDown(int index) {

        while (index <= size) {

            int lc = leftChild(index);

            int rc = rightChild(index);

            if (lc > size)
                return;

            // 比左边元素小 && 左边 >= 右边
            if (comparator.compare(nodes[lc], nodes[index]) < 0 && (rc > size || comparator.compare(nodes[lc], nodes[rc]) <= 0)) {
                swap(index, lc);
                index = lc;
                // 比右边元素小
            } else if (rc <= size && comparator.compare(nodes[rc], nodes[index]) < 0) {
                swap(index, rc);
                index = rc;
            } else {
                break;
            }

        }

    }

    private void swap(int a, int b) {
        // 先交换 nodes
        T temp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = temp;

        // 更新 indexes
        indexes.put(keyGetter.apply(nodes[a]), a);
        indexes.put(keyGetter.apply(nodes[b]), b);
    }

}
