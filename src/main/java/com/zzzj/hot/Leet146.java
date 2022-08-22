package com.zzzj.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-04-15 12:03
 */
public class Leet146 {


    public static void main(String[] args) {
        // ["LRUCache","put","get","put","get","get"]
        //[[1],[2,1],[2],[3,2],[2],[3]]
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        cache.get(2);
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }

    static class LRUCache {

        private final int capacity;

        private static class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private void addToHead(Node node) {
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            node.next = head;
            head.prev = node;
            head = node;
        }

        private void moveToHead(Node node) {
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            if (node == head) {
                return;
            }

            // 上一个
            Node prev = node.prev;

            // 上.next = cur.next

            // cur.next.prev = prev
            prev.next = node.next;
            if (node.next != null) {
                node.next.prev = prev;
            } else {
                // 当前节点是tail
                tail = prev;
            }

            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }

        private void removeTail() {
            if (tail == null) {
                return;
            }
            if (tail == head) {
                tail = null;
                head = null;
                return;
            }
            Node prev = tail.prev;
            tail = prev;
            prev.next = null;
        }

        Node head;
        Node tail;

        private Map<Integer, Node> map = new HashMap<>();

        private int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node == null) {
                Node newNode = new Node(key, value);
                addToHead(newNode);
                size++;
                if (size > capacity) {
                    int delKey = tail.key;
                    removeTail();
                    map.remove(delKey);
                }
                map.put(key, newNode);
            } else {
                moveToHead(node);
                node.value = value;
            }
        }

    }

}
