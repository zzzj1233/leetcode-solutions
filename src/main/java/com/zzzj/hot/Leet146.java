package com.zzzj.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-04-15 12:03
 */
public class Leet146 {


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 1);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class LinkList {
        Node head;
        Node tail;

        public Node removeHead() {
            if (head == null) {
                return null;
            }
            Node ret = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                if (head.next != null) {
                    head.next.prev = null;
                }
                head = head.next;
            }
            return ret;
        }

        public void moveNodeToTail(Node node) {
            if (node == tail) {
                return;
            }
            if (node == head) {
                head = node.next;
                head.prev = null;
                node.next = null;
                node.prev = tail;
                tail.next = node;
                tail = node;
            } else {
                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
                node.prev = tail;
                tail.next = node;
                tail = node;
            }
        }

        public void addLast(Node node) {
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            node.prev = tail;
            tail.next = node;
            tail = node;
        }

    }

    static class LRUCache {

        // HashMap + 双端链表
        private final int capacity;

        private final Map<Integer, Node> map;

        private final LinkList list;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            list = new LinkList();
        }

        public int get(int key) {
            Node node = map.get(key);

            if (node == null) {
                return -1;
            }

            // 将node放入队尾
            this.list.moveNodeToTail(node);

            return node.val;
        }

        public void put(int key, int value) {
            Node node = map.get(key);

            if (node != null) {
                node.val = value;
                list.moveNodeToTail(node);
                return;
            }

            Node newNode = new Node(key, value);

            list.addLast(newNode);

            int size = map.size();

            map.put(key, newNode);

            // remove head
            if (size == capacity) {
                Node removed = list.removeHead();
                if (removed != null) {
                    map.remove(removed.key);
                }
            }

        }

    }

}
