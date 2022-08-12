package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-08-12 17:27
 */
public class Leet623 {

    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int level = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            level++;

            for (int i = 0; i < size; i++) {

                TreeNode last = queue.removeFirst();

                if (level + 1 == depth) {
                    TreeNode originLeft = last.left;
                    TreeNode originRight = last.right;

                    TreeNode left = new TreeNode(val);
                    TreeNode right = new TreeNode(val);

                    left.left = originLeft;
                    right.right = originRight;

                    last.left = left;
                    last.right = right;
                    continue;
                }

                if (last.left != null) {
                    queue.add(last.left);
                }

                if (last.right != null) {
                    queue.add(last.right);
                }


            }

        }

        return root;
    }

}
