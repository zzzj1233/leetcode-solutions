package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-08-22 11:44
 */
public class Leet705 {

    private static class MyHashSet {

        private MyHashMap map = new MyHashMap();

        public MyHashSet() {

        }

        public void add(int key) {
            map.put(key, 0);
        }

        public void remove(int key) {
            map.remove(key);
        }

        public boolean contains(int key) {
            return map.get(key) >= 0;
        }

        public static class MyHashMap {

            private static final float LOAD_FACTOR = 0.75F;

            private int capacity = 16;

            private int size;

            private int threshold;

            private MyHashMap.Node[] table;

            public MyHashMap() {
                table = new MyHashMap.Node[capacity];
                threshold = (int) (capacity * LOAD_FACTOR);
            }

            private void resize() {
                int newCapacity = capacity << 1;
                int newThreshold = (int) (capacity * LOAD_FACTOR);

                MyHashMap.Node[] newTable = new MyHashMap.Node[newCapacity];

                MyHashMap.Node[] oldTable = table;

                for (int i = 0; i < capacity; i++) {
                    MyHashMap.Node node = table[i];

                    while (node != null) {
                        MyHashMap.Node next = node.next;

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

            private void put(int key, int value, MyHashMap.Node[] table, boolean addSize) {
                int hash = hash(key);
                int index = hash % table.length;

                if (table[index] == null) {
                    table[index] = new MyHashMap.Node(key, value);
                    if (addSize) {
                        size++;
                    }
                } else {
                    MyHashMap.Node prev = table[index];
                    MyHashMap.Node start = table[index];
                    while (start != null) {
                        if (start.key == key) {
                            start.value = value;
                            return;
                        }
                        prev = start;
                        start = start.next;
                    }
                    prev.next = new MyHashMap.Node(key, value);
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
                    MyHashMap.Node start = table[hash];
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
                    MyHashMap.Node prev = table[hash];
                    MyHashMap.Node cur = table[hash].next;
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
                private MyHashMap.Node next;
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
}