package com.zzzj.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-08-13 16:19
 */
public class Leet919 {


    private static class CBTInserter {

        private ArrayList<TreeNode> nodes = new ArrayList<>();

        public CBTInserter(TreeNode root) {
            LinkedList<TreeNode> queue = new LinkedList<>();

            queue.add(root);

            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    TreeNode first = queue.removeFirst();

                    if (first.left != null) {
                        queue.addLast(first.left);
                    }

                    if (first.right != null) {
                        queue.addLast(first.right);
                    }

                    nodes.add(first);
                }
            }

        }

        public int insert(int val) {
            TreeNode newNode = new TreeNode(val);

            nodes.add(newNode);

            int size = nodes.size();

            if (size == 1) {
                return nodes.get(0).val;
            }

            TreeNode parent = nodes.get((size - 2) / 2);

            if (size % 2 == 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }

            return parent.val;
        }

        public TreeNode get_root() {
            return nodes.isEmpty() ? null : nodes.get(0);
        }
    }


}
