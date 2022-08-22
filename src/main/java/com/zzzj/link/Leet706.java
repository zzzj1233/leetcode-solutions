package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-08-22 11:08
 */
public class Leet706 {

    private static class MyHashMap {

        private static final float LOAD_FACTOR = 0.75F;

        private int capacity = 16;

        private int size;

        private int threshold;

        private Node[] table;

        public MyHashMap() {
            table = new Node[capacity];
            threshold = (int) (capacity * LOAD_FACTOR);
        }

        private void resize() {
            int newCapacity = capacity << 1;
            int newThreshold = (int) (capacity * LOAD_FACTOR);

            Node[] newTable = new Node[newCapacity];

            Node[] oldTable = table;

            for (int i = 0; i < capacity; i++) {
                Node node = table[i];

                while (node != null) {
                    Node next = node.next;

                    node.next = null;

                    put(node.key, node.value, newTable, false);

                    node = next;
                }
            }

            table = newTable;
            capacity = newCapacity;
            threshold = newThreshold;
        }

        private int hash(int value) {
            return Integer.hashCode(value);
        }

        public void put(int key, int value) {
            put(key, value, table, true);
            if (size >= threshold) {
                resize();
            }
        }

        private void put(int key, int value, Node[] table, boolean addSize) {
            int hash = hash(key);
            int index = hash % table.length;

            if (table[index] == null) {
                table[index] = new Node(key, value);
                if (addSize) {
                    size++;
                }
            } else {
                Node prev = table[index];
                Node start = table[index];
                while (start != null) {
                    if (start.key == key) {
                        start.value = value;
                        return;
                    }
                    prev = start;
                    start = start.next;
                }
                prev.next = new Node(key, value);
                if (addSize) {
                    size++;
                }
            }
        }

        public int get(int key) {
            int hash = hash(key) % capacity;
            if (table[hash] == null) {
                return -1;
            } else {
                Node start = table[hash];
                while (start != null) {
                    if (start.key == key) {
                        return start.value;
                    }
                    start = start.next;
                }
            }
            return -1;
        }

        public void remove(int key) {
            int hash = hash(key) % capacity;
            if (table[hash] == null) {
                return;
            } else if (table[hash].key == key) {
                table[hash] = table[hash].next;
                size--;
            } else {
                Node prev = table[hash];
                Node cur = table[hash].next;
                while (cur != null) {
                    if (cur.key == key) {
                        prev.next = cur.next;
                        size--;
                        return;
                    }
                    prev = cur;
                    cur = cur.next;
                }
            }
        }

        private static class Node {
            private Node next;
            private int value;
            private int key;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public Node() {
            }
        }
    }


}
