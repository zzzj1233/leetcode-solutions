package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 18:53
 */
public class Leet1372 {

    public static void main(String[] args) {
        System.out.println(longestZigZag(TreeNode.buildTree("[1,1,1,null,1,null,null,1,1,null,1]")));
    }


    public static int longestZigZag(TreeNode root) {
        int[] dfs = dfs(root);

        return Math.max(dfs[0], Math.max(dfs[1], dfs[2])) - 1;
    }

    public static int[] dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new int[]{1, 1, 1};
        }

        if (root.left != null && root.right != null) {
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);

            int[] result = new int[3];

            result[0] = left[1] + 1;
            result[1] = right[0] + 1;
            result[2] = Math.max(left[2], Math.max(result[0], Math.max(result[1], right[2])));
            return result;
        } else if (root.left != null) {
            int[] left = dfs(root.left);
            int[] result = new int[3];
            result[0] = left[1] + 1;
            result[1] = 1;
            result[2] = Math.max(result[0], left[2]);
            return result;
        } else {
            int[] right = dfs(root.right);
            int[] result = new int[3];
            result[0] = 1;
            result[1] = right[0] + 1;
            result[2] = Math.max(result[1], right[2]);
            return result;
        }

    }


}
