package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 19:30
 */
public class Leet1026 {

    static int ans;

    public static int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ans = 0;
        dfs(root, root.val, root.val);
        return ans;
    }

    public static void dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }

        ans = Math.max(ans, Math.abs(root.val - min));
        ans = Math.max(ans, Math.abs(max - root.val));

        dfs(root.left, Math.min(min, root.val), Math.max(max, root.val));
        dfs(root.right, Math.min(min, root.val), Math.max(max, root.val));
    }

}
