package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-11 18:57
 */
public class Leet663 {

    static int target;

    static boolean ans;

    public static boolean checkEqualTree(TreeNode root) {
        int sum = dfs(root);

        target = sum / 2;

        ans = false;

        dfs(root);

        return ans;
    }

    public static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (ans) {
            return -1;
        }
        int leftVal = dfs(root.left);
        int rightVal = dfs(root.right);
        if (root.val + leftVal == target || root.val + rightVal == target || root.val + leftVal + rightVal == target) {
            ans = true;
        }
        return root.val + leftVal + rightVal;
    }

}
