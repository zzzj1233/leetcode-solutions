package com.zzzj.leet;


/**
 * @author Zzzj
 * @create 2022-06-05 15:12
 */
public class Leet421 {

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

    public static int findMaximumXOR(int[] nums) {

        BinaryTrie binaryTrie = new BinaryTrie();

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            binaryTrie.addNum(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, binaryTrie.findMax(nums[i]));
        }

        return ans;
    }

    private static class BinaryTrie {
        Node node = new Node();

        public int findMax(int num) {
            int ans = 0;

            Node node = this.node;

            for (int i = 31; i >= 0; i--) {
                // 如果当前位是1,那么期望0,如果当前位是0,那么期望1
                int expect = ((num >> i) & 1) == 1 ? 0 : 1;

                // 期望1
                if (expect == 1) {
                    if (node.one != null) {
                        ans = (ans << 1) + 1;
                        node = node.one;
                    } else {
                        ans <<= 1;
                        node = node.zero;
                    }
                } else {
                    if (node.zero != null) {
                        ans = (ans << 1) + 1;
                        node = node.zero;
                    } else {
                        ans <<= 1;
                        node = node.one;
                    }
                }
            }

            return ans;
        }

        public void addNum(int num) {
            Node node = this.node;
            for (int i = 31; i >= 0; i--) {
                if (((num >> i) & 1) == 1) {
                    if (node.one == null) {
                        node.one = new Node();
                    }
                    node = node.one;
                } else {
                    if (node.zero == null) {
                        node.zero = new Node();
                    }
                    node = node.zero;
                }
            }
        }
    }

    private static class Node {
        public Node zero;
        public Node one;
    }

}
