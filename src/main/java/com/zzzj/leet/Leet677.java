package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-07-20 15:51
 */
public class Leet677 {

    public static void main(String[] args) {
        final MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }

    private static class MapSum {

        private final Tire tire;

        private static class Tire {
            Tire[] next;
            int value;

            public Tire() {
                next = new Tire[26];
            }
        }

        public MapSum() {
            this.tire = new Tire();
        }

        public void insert(String key, int val) {
            Tire node = tire;

            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                Tire next = node.next[c - 'a'];
                if (next == null) {
                    next = new Tire();
                    node.next[c - 'a'] = next;
                }
                node = next;
            }
            node.value = val;
        }

        private int getValue(Tire node) {
            int sum = node.value;
            for (int i = 0; i < node.next.length; i++) {
                if (node.next[i] != null) {
                    sum += getValue(node.next[i]);
                }
            }
            return sum;
        }

        public int sum(String prefix) {
            int ans = 0;

            Tire node = tire;

            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                node = node.next[c - 'a'];
                if (node == null) {
                    return 0;
                }
            }
            return getValue(node);
        }

    }

}
