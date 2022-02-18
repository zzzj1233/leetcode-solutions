package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-08 14:10
 */
public class Leet98 {

    public static void main(String[] args) {

    }

    private static class Info {
        public boolean isBst;
        public int min;
        public int max;
    }

    private static Info dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);

        Info info = new Info();

        info.min = leftInfo == null ? root.val : Math.min(leftInfo.min, root.val);
        info.max = rightInfo == null ? root.val : Math.max(rightInfo.max, root.val);

        info.isBst = true;

        if (leftInfo != null) {
            if (!leftInfo.isBst) {
                info.isBst = false;
            }
            if (leftInfo.max > root.val) {
                info.isBst = false;
            }
        }

        if (rightInfo != null) {
            if (!rightInfo.isBst) {
                info.isBst = false;
            }
            if (rightInfo.min < root.val) {
                info.isBst = false;
            }
        }

        return info;
    }

    public static boolean isValidBST(TreeNode root) {
        return dfs(root).isBst;
    }

}
