package com.zzzj.tree;

import com.zzzj.leet.LeetUtils;
import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-08 16:16
 */
public class Leet543 {

    public static void main(String[] args) {
        String tree = LeetUtils.randomTreeStr(10);
        System.out.println(diameterOfBinaryTree(TreeNode.buildTree("[1,2,3,4,5]")));
    }

    private static class Info {
        public int maxDs;
        public int height;
    }

    private static Info dfs(TreeNode root) {
        if (root == null) {
            return new Info();
        }

        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);

        Info info = new Info();

        info.height = Math.max(leftInfo.height, rightInfo.height) + 1;
        info.maxDs = Math.max(leftInfo.maxDs, Math.max(rightInfo.maxDs, leftInfo.height + rightInfo.height));
        return info;
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        Info info = dfs(root);
        return info.maxDs;
    }


}
