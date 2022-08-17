package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-08-17 12:03
 */
public class Leet968 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        for (int i = 0; i < 1000; i++) {
            TreeNode tree = LeetUtils.randomTree2(100);
            if (minCameraCover(tree) != solution.minCameraCover(tree)) {
                System.out.println("Error");
                return;
            }
        }
    }

    public static int minCameraCover(TreeNode root) {
        int[] dfs = dfs(root);
        return Math.min(dfs[0], dfs[1]);
    }

    // // 0：该节点安装了监视器 1：该节点可观，但没有安装监视器 2：该节点不可观
    public static int[] dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new int[]{1, -1, 0};
        }

        if (root.left != null && root.right != null) {

        }

        return null;
    }

    private static class Solution {
        private int ans = 0;

        public int minCameraCover(TreeNode root) {
            if (root == null) return 0;
            if (dfs(root) == 2) ans++;
            return ans;
        }

        // 0：该节点安装了监视器 1：该节点可观，但没有安装监视器 2：该节点不可观
        private int dfs(TreeNode node) {
            if (node == null)
                return 1;
            int left = dfs(node.left), right = dfs(node.right);
            if (left == 2 || right == 2) {
                ans++;
                return 0;
            } else if (left == 0 || right == 0) {
                return 1;
            } else
                return 2;
        }
    }

}



