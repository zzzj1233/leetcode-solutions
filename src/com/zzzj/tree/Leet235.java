package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-08 17:39
 */
public class Leet235 {

    private static TreeNode pNode;

    private static TreeNode qNode;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        pNode = p;
        qNode = q;
        return dfs(root).answer;
    }

    private static class Info {
        public boolean leftHas;
        public boolean rightHas;
        public TreeNode answer;
    }

    public static Info dfs(TreeNode root) {
        if (root == null) {
            return new Info();
        }
        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);

        Info info = new Info();
        info.leftHas = leftInfo.leftHas || rightInfo.leftHas || root.val == pNode.val;
        info.rightHas = leftInfo.rightHas || rightInfo.rightHas || root.val == qNode.val;

        if (leftInfo.answer != null) {
            info.answer = leftInfo.answer;
        } else if (rightInfo.answer != null) {
            info.answer = rightInfo.answer;
        } else if (info.leftHas && info.rightHas) {
            info.answer = root;
        }

        return info;
    }

}
