package com.zzzj.tree;

public class Leet2331 {

    public static boolean evaluateTree(TreeNode root) {
        if (root.left == null) {
            return root.val == 1;
        }
        return root.val == 2 ? (evaluateTree(root.left) || evaluateTree(root.right)) : (evaluateTree(root.left) && evaluateTree(root.right));
    }

}
