package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-10 15:50
 */
public class Leet563 {

    public static void main(String[] args) {
        System.out.println(findTilt(TreeNode.buildTree("[21,7,14,1,1,2,2,3,3]")));
    }

    public static int findTilt(TreeNode root) {
        return dfs(root).tilt;
    }

    private static class Info {
        public int total;
        public int tilt;
    }

    private static Info dfs(TreeNode root) {
        if (root == null) {
            return new Info();
        }
        if (root.left == null && root.right == null) {
            Info info = new Info();
            info.total = root.val;
            return info;
        }
        Info leftInfo = dfs(root.left);

        Info rightInfo = dfs(root.right);

        Info info = new Info();

        info.total = root.val + leftInfo.total + rightInfo.total;

        info.tilt = leftInfo.tilt + rightInfo.tilt + Math.abs(leftInfo.total - rightInfo.total);

        return info;
    }

}
