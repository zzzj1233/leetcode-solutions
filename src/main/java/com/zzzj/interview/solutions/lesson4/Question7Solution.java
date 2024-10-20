package com.zzzj.interview.solutions.lesson4;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-07-02 14:31
 */
public class Question7Solution {


    public static void main(String[] args) {
        TopRecord topRecord = new TopRecord(2);

        topRecord.add("a");
        topRecord.add("b");
        topRecord.add("c");
        topRecord.add("a");
        topRecord.add("c");

        System.out.println(topRecord.top());

        topRecord.add("b");
        topRecord.add("b");

        System.out.println(topRecord.top());

        topRecord.add("a");

        System.out.println(topRecord.top());

        topRecord.add("d");
        topRecord.add("d");
        topRecord.add("d");
        topRecord.add("d");
        topRecord.add("d");
        topRecord.add("c");
        topRecord.add("c");

        System.out.println(topRecord.top());
    }

    private static class TopRecord {

        Node[] heap;
        final int maxSize;
        int count;

        Map<String, Node> wordMap;
        Map<Node, Integer> indexMap;

        public TopRecord(int k) {
            heap = new Node[k + 1];
            maxSize = k;
            wordMap = new HashMap<>();
            indexMap = new HashMap<>();
        }

        // 添加一个字符串,可重复
        public void add(String str) {
            Node node = wordMap.get(str);
            // 第一次被统计
            if (node == null) {
                node = new Node(str, 0);
                wordMap.put(str, node);
                indexMap.put(node, -1);
            }
            node.count++;
            Integer heapIndex = indexMap.get(node);

            // 没在堆内
            if (heapIndex == -1) {
                if (count < maxSize) {
                    push(node);
                } else {
                    // 堆满了,但是最小堆的最小的count小于当前count
                    if (peek().count < node.count) {
                        pop();
                        push(node);
                    }
                }
            } else {
                // shiftDown
                shiftDown(heapIndex);
            }
        }

        // 返回词频最大的K个字符串
        public List<String> top() {

            LinkedList<String> result = new LinkedList<>();

            LinkedList<Node> nodes = new LinkedList<>();

            while (count > 0) {
                Node pop = pop();
                result.addFirst(pop.str);
                nodes.add(pop);
            }

            nodes.forEach(this::push);

            return result;
        }

        public Node peek() {
            if (count == 0) {
                throw new NoSuchElementException("Heap is empty");
            }
            return heap[1];
        }

        public void push(Node node) {
            heap[count + 1] = node;
            shiftUp(count + 1);
            indexMap.put(node, count + 1);
            count++;
        }


        public Node pop() {
            if (count == 0) {
                throw new NoSuchElementException("Heap is empty");
            }
            Node node = heap[1];

            // 把最后一个元素放到第一个来,shiftDown
            swap(1, count);

            count--;

            shiftDown(1);

            // 表明该元素不在堆内了
            indexMap.put(node, -1);

            return node;
        }

        public void swap(int i, int j) {
            indexMap.put(heap[i], j);
            indexMap.put(heap[j], i);

            Node temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }


        public void shiftUp(int index) {
            while (index / 2 > 0 && heap[index].count < heap[index / 2].count) {
                swap(index / 2, index);
                index /= 2;
            }
        }

        public void shiftDown(int index) {
            int left = leftChild(index);

            if (left > count) {
                return;
            }

            int right = rightChild(index);

            int leftValue = heap[left].count;
            int rightValue = right > count ? Integer.MAX_VALUE : heap[right].count;

            if (leftValue < heap[index].count && leftValue < rightValue) {
                swap(left, index);
                shiftDown(left);
            } else if (rightValue < heap[index].count) {
                swap(right, index);
                shiftDown(right);
            }

        }

        private int leftChild(int index) {
            return index << 1;
        }

        private int rightChild(int index) {
            return (index << 1) + 1;
        }

    }

    private static class Node {
        String str;
        int count;

        public Node(String str, int count) {
            this.str = str;
            this.count = count;
        }

    }

}
