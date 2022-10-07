package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-10-07 12:58
 */
public class Leet1973 {


    static int ans;

    public static int equalToDescendants(TreeNode root) {
        ans = 0;

        dfs(root);

        return ans;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);

        if (root.val == leftSum + rightSum) {
            ans++;
        }

        return leftSum + rightSum + root.val;
    }

}
