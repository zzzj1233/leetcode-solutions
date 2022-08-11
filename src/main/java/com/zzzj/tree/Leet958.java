package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.LinkedList;


/**
 * @author zzzj
 * @create 2021-11-08 10:31
 */
public class Leet958 {

    public static void main(String[] args) {

    }

    public static boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        boolean stop = false;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode first = queue.removeFirst();
                if (first.left == null && first.right != null) {
                    return false;
                }
                if (stop && (first.left != null || first.right != null)) {
                    return false;
                }
                if (first.left == null || first.right == null) {
                    stop = true;
                }
                if (first.left != null) {
                    queue.add(first.left);
                }
                if (first.right != null) {
                    queue.add(first.right);
                }
            }
        }

        return true;
    }


}
