package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-08 10:23
 */
public class Leet2265 {

    static final int[] EMPTY = new int[2];

    static int ans;

    public static int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ans = 0;
        dfs(root);
        return ans;
    }

    public static int[] dfs(TreeNode root) {
        int[] left = EMPTY;
        int[] right = EMPTY;

        if (root.left != null) {
            left = dfs(root.left);
        }

        if (root.right != null) {
            right = dfs(root.right);
        }

        int value = left[0] + right[0] + root.val;
        int count = left[1] + right[1] + 1;

        if (value / count == root.val) {
            ans++;
        }

        return new int[]{value, count};
    }

}
