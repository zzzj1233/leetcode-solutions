package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-04-01 17:18
 */
public class Leet1379 {

    public TreeNode getTargetCopy(TreeNode original,
                                  TreeNode cloned,
                                  TreeNode target) {
        if (original == null || cloned == null || target == null) {
            return null;
        }
        return dfs(cloned, target);
    }

    public TreeNode dfs(TreeNode original, TreeNode target) {
        if (target.val == original.val) {
            return original;
        }
        if (original.left != null) {
            TreeNode result = dfs(original.left, target);
            if (result != null) {
                return result;
            }
        }
        if (original.right != null) {
            TreeNode result = dfs(original.right, target);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

}
