package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-10 16:07
 */
public class Leet513 {

    private static int ans;

    private static int curLevel;

    public static void main(String[] args) {
        System.out.println(findBottomLeftValue(TreeNode.buildTree("[2,1,3]")));
    }

    public static int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        curLevel = 0;
        ans = 0;
        dfs(root, 1);
        return ans;
    }

    private static void dfs(TreeNode root, int level) {
        if (root.left != null) {
            dfs(root.left, level + 1);
        }

        if (root.right != null) {
            dfs(root.right, level + 1);
        }

        if (level > curLevel) {
            ans = root.val;
            curLevel = level;
        }
    }

}
