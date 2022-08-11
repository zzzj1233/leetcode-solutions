package com.zzzj.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-08-09 12:12
 */
public class Leet1660 {

    public static void main(String[] args) {
    }

    public static TreeNode correctBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        return dfs(root, new HashSet<>());
    }

    public static TreeNode dfs(TreeNode root, Set<TreeNode> nodes) {
        if (root.right != null) {
            if (nodes.contains(root.right)) {
                return null;
            }
            root.right = dfs(root.right, nodes);
        }

        nodes.add(root);

        if (root.left != null) {
            root.left =  dfs(root.left, nodes);
        }

        return root;
    }

}
