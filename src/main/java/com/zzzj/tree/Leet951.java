package com.zzzj.tree;

/**
 * @author Zzzj
 * @create 2022-08-13 16:37
 */
public class Leet951 {

    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val == root2.val) {
            return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right));
        }

        return false;
    }

}
