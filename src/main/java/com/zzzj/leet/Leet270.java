package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-11 12:12
 */
public class Leet270 {

    public static int ans;
    public static double min;

    public static int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }
        ans = 0;
        min = Integer.MAX_VALUE;

        dfs(root, target);
        return ans;
    }

    public static void dfs(TreeNode root, double target) {
        double sub = Math.abs(target - root.val);

        if (sub < min) {
            ans = root.val;
            min = sub;
        }

        if (root.val < target && root.right != null) {
            dfs(root.right, target);
        }

        if (root.val > target && root.left != null) {
            dfs(root.left, target);
        }

    }

}
