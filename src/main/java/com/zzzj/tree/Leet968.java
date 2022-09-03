package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-08-17 12:03
 */
public class Leet968 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            TreeNode tree = LeetUtils.randomTree2(10);
            if (minCameraCover(tree) != new Solution().minCameraCover(tree)) {
                System.out.println("Error");
                System.out.println("[" + tree.serialize() + "]");
                System.out.println(minCameraCover(tree));
                System.out.println(new Solution().minCameraCover(tree));
                return;
            }
        }
    }

    public static int minCameraCover(TreeNode root) {
        int[] dfs = dfs(root);
        return Math.min(dfs[0], dfs[1]);
    }

    // 0.该节点安装了监视器
    // 1.该节点可观,但没有安装监视器
    // 2.该节点不可观
    public static int[] dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new int[]{1, Integer.MAX_VALUE / 3, 0};
        }
        if (root.left == null) {
            int[] right = dfs(root.right);

            int[] result = new int[3];

            result[0] = Math.min(right[0], Math.min(right[1], right[2])) + 1;
            result[1] = right[0];
            result[2] = Math.min(right[0], right[1]);
            return result;
        } else if (root.right == null) {
            int[] left = dfs(root.left);

            int[] result = new int[3];

            result[0] = Math.min(left[0], Math.min(left[1], left[2])) + 1;
            result[1] = left[0];
            result[2] = Math.min(left[0], left[1]);
            return result;
        } else {
            int[] result = new int[3];

            int[] left = dfs(root.left);
            int[] right = dfs(root.right);

            result[0] = Integer.MAX_VALUE;

            for (int i : left) {
                for (int j : right) {
                    result[0] = Math.min(result[0], i + j + 1);
                }
            }

            result[1] = Math.min(left[0] + right[0], Math.min(left[0] + right[1], left[1] + right[0]));
            result[2] = Math.min(Math.min(left[0] + right[1], left[0] + right[0]), Math.min(left[1] + right[0], left[1] + right[1]));

            return result;
        }
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



