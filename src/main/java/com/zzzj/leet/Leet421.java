package com.zzzj.leet;


/**
 * @author Zzzj
 * @create 2022-06-05 15:12
 */
public class Leet421 {

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
        System.out.println(findMaximumXOR(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70}));
    }

    public static int findMaximumXOR(int[] nums) {

        Trie trie = new Trie();

        for (int num : nums) {
            build(num, trie);
        }

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Trie node = trie;

            int max = 0;
            for (int j = 31; j >= 0; j--) {
                int bit = (num >> j) & 1;

                if (bit == 1) {
                    // 尝试找0
                    if (node.next[0] != null) {
                        max |= 1 << j;
                        node = node.next[0];
                    } else {
                        node = node.next[1];
                    }

                } else {
                    // 尝试找1
                    if (node.next[1] != null) {
                        max |= 1 << j;
                        node = node.next[1];
                    } else {
                        node = node.next[0];
                    }
                }
            }

            ans = Math.max(ans, max);
        }

        return ans;
    }

    public static void build(int num, Trie node) {
        for (int i = 31; i >= 0; i--) {
            if (((num >> i) & 1) == 1) {
                if (node.next[1] == null) {
                    node.next[1] = new Trie();
                }
                node = node.next[1];
            } else {
                if (node.next[0] == null) {
                    node.next[0] = new Trie();
                }
                node = node.next[0];
            }
        }
    }

    private static class Trie {
        Trie[] next;

        public Trie() {
            next = new Trie[2];
        }
    }


}
