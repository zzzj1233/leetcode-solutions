package com.zzzj.tree;


/**
 * @author zzzj
 * @create 2022-08-16 12:22
 */
public class Leet1261 {

    private static class FindElements {

        private final TreeNode root;

        public FindElements(TreeNode root) {
            this.root = root;
        }

        public boolean find(int target) {
            return dfs(root, target, 0);
        }

        public boolean dfs(TreeNode root, int target, int cur) {
            if (root == null) {
                return false;
            }
            if (cur == target) {
                return true;
            }
            int left = (cur << 1) + 1;
            int right = (cur << 1) + 2;
            if (left > target) {
                return false;
            }
            if (right > target) {
                return dfs(root.left, target, left);
            }
            return dfs(root.left, target, left) || dfs(root.right, target, right);
        }

    }

}
