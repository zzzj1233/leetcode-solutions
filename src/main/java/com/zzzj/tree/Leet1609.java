package com.zzzj.tree;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-07-10 17:43
 */
public class Leet1609 {


    public static boolean isEvenOddTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        boolean even = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            int max = Integer.MIN_VALUE;

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode first = queue.removeFirst();
                if (even) {
                    if (((first.val & 1) != 1)) {
                        return false;
                    }
                    if (first.val <= max) {
                        return false;
                    }
                    max = first.val;
                } else {
                    if (((first.val & 1) == 1)) {
                        return false;
                    }
                    if (first.val >= min) {
                        return false;
                    }
                    min = first.val;
                }
                if (first.left != null) {
                    queue.addLast(first.left);
                }
                if (first.right != null) {
                    queue.addLast(first.right);
                }
            }

            even = !even;
        }

        return true;
    }

}
