package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-11-12 11:48
 */
public class BSTIterator {

    private LinkedList<Integer> path;

    public BSTIterator(TreeNode root) {
        path = new LinkedList<>();
        if (root != null) {
            inOrder(root);
        }
    }

    private void inOrder(TreeNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        path.add(root.val);
        if (root.right != null) {
            inOrder(root.right);
        }
    }

    public int next() {
        return path.removeFirst();
    }

    public boolean hasNext() {
        return !path.isEmpty();
    }

}
