package com.zzzj.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-08-08 10:40
 */
public class Leet1676 {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }

        Set<Integer> set = new HashSet<>(nodes.length);

        for (TreeNode node : nodes) {
            set.add(node.val);
        }

        TreeNode ret = dfs(root, set);

        if (ret != null) {
            return ret;
        }

        return root;
    }

    public static TreeNode dfs(TreeNode root, Set<Integer> nodes) {
        if (root.left == null && root.right == null) {
            return null;
        }

        if (root.left != null && root.right != null) {
            TreeNode ret = dfs(root.left, nodes);

            if (ret != null) {
                return ret;
            }

            ret = dfs(root.right, nodes);

            if (ret != null) {
                return ret;
            }

            nodes.remove(root.right.val);
            nodes.remove(root.left.val);
        } else if (root.right != null) {
            TreeNode ret = dfs(root.right, nodes);

            if (ret != null) {
                return ret;
            }
            nodes.remove(root.right.val);
            if (nodes.isEmpty()){
                return root.right;
            }
        } else {
            TreeNode ret = dfs(root.left, nodes);

            if (ret != null) {
                return ret;
            }
            nodes.remove(root.left.val);

            if (nodes.isEmpty()) {
                return root.left;
            }
        }

        nodes.remove(root.val);

        if (nodes.isEmpty()) {
            return root;
        }

        return null;
    }
}
