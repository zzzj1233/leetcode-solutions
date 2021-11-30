package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-16 11:29
 */
public class Leet236 {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        return dfs(root, p, q).answer;
    }

    private static class Info {
        public boolean hasLeft;
        public boolean hasRight;
        public TreeNode answer;
    }

    private static Info dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info();
        }
        Info leftInfo = dfs(root.left, p, q);
        Info rightInfo = dfs(root.right, p, q);

        Info info = new Info();

        if (leftInfo.answer != null) {
            info.answer = leftInfo.answer;
            return info;
        }

        if (rightInfo.answer != null) {
            info.answer = rightInfo.answer;
            return info;
        }

        info.hasLeft = leftInfo.hasLeft || rightInfo.hasLeft || root == p;
        info.hasRight = leftInfo.hasRight || rightInfo.hasRight || root == q;

        if (info.hasLeft && info.hasRight) {
            info.answer = root;
        }

        return info;
    }

}
