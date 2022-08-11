package com.zzzj.tree;

import java.util.Arrays;

public class Leet1612 {

    class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static int[] counter1 = new int[26];
    static int[] counter2 = new int[26];

    public static boolean checkEquivalence(Node root1, Node root2) {
        Arrays.fill(counter1, 0);
        Arrays.fill(counter2, 0);
        dfs(root1, counter1);
        dfs(root2, counter2);
        return Arrays.equals(counter1, counter2);
    }

    public static void dfs(Node root, int[] counter) {
        if (root == null) {
            return;
        }

        if (root.val == '+') {
            dfs(root.left, counter);
            dfs(root.right, counter);
        } else {
            counter[root.val - 'a']++;
        }
    }

}
