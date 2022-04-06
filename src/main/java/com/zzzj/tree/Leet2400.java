package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2022-04-01 16:29
 */
public class Leet2400 {

    public static void main(String[] args) {
        final BSTIterator bstIterator = new BSTIterator(TreeNode.buildTree("[7,3,15,null,null,9,20]"));

        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }

    }

    static class BSTIterator {

        private TreeNode root;

        private TreeNode pre;

        public BSTIterator(TreeNode root) {
            inOrder(root);
        }

        public void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            if (pre != null) {
                pre.right = root;
            } else {
                this.root = root;
            }
            pre = root;
            inOrder(root.right);
        }

        public int next() {
            final int val = root.val;
            root = root.right;
            return val;
        }

        public boolean hasNext() {
            return root != null;
        }
    }

}
