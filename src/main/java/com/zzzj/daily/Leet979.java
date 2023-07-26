package com.zzzj.daily;

import com.zzzj.leet.TreeNode;

public class Leet979 {

    public static void main(String[] args) {

        System.out.println(distributeCoins(TreeNode.buildTree("[3,0,0]")));

        System.out.println(distributeCoins(TreeNode.buildTree("[0,3,0]")));

        System.out.println(distributeCoins(TreeNode.buildTree("[0,0,0,0,0,0,7]")));

    }

    public static int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }


    static int ans;

    public static int dfs(TreeNode root) {

        if (root == null) return 0;

        int lc = dfs(root.left);

        int rc = dfs(root.right);

        ans += Math.abs(lc) + Math.abs(rc);

        return lc + rc + root.val - 1;
    }

}
