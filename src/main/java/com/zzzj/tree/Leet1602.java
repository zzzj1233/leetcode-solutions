package com.zzzj.tree;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-08-09 12:04
 */
public class Leet1602 {

    public static TreeNode findNearestRightNode(TreeNode root, TreeNode u) {

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();

                if (node == u) {
                    if (i == size - 1) {
                        return null;
                    } else {
                        return queue.peekFirst();
                    }
                }

                if (node.left != null) {
                    queue.addLast(node.left);
                }

                if (node.right != null) {
                    queue.addLast(node.right);
                }

            }
        }

        return null;
    }
}
