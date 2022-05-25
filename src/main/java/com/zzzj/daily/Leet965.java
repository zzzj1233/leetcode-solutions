package com.zzzj.daily;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2022-05-24 12:05
 */
public class Leet965 {

    public static boolean isUnivalTree(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) {
            return true;
        }
        int val = root.val;

        if (root.left == null) {
            return val == root.right.val && isUnivalTree(root.right);
        }

        if (root.right == null) {
            return val == root.left.val && isUnivalTree(root.left);
        }

        return val == root.left.val && val == root.right.val && isUnivalTree(root.left) && isUnivalTree(root.right);
    }

}
