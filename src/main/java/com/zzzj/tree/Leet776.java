package com.zzzj.tree;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-11 10:40
 */
public class Leet776 {

    public static void main(String[] args) {
        // 5,7,4,3,6,null,null,2
        System.out.println(Arrays.toString(splitBST(TreeNode.buildTree("[10,5,20,3,9,15,25,null,null,8,null,null,null,null,null,6,null,null,7]"), 6)));
    }

    public static TreeNode[] splitBST(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[]{};
        }

        return dfs(root, target);
    }

    public static TreeNode[] dfs(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }

        if (root.val > target) {
            TreeNode[] dfs = dfs(root.left, target);
            root.left = dfs[1];
            return new TreeNode[]{dfs[0], root};
        } else {
            TreeNode[] dfs = dfs(root.right, target);
            root.right = dfs[0];
            return new TreeNode[]{root, dfs[1]};
        }
    }


}
