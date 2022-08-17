package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-11-22 16:43
 */
public class LeetOffer53 {

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (p.right != null) {
            return leftist(p.right);
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        buildParent(root, null, parentMap);

        TreeNode node = p;

        while (true) {
            TreeNode parent = parentMap.get(node);
            if (parent == null) {
                return null;
            }
            if (node == parent.right) {
                node = parent;
            } else {
                return parent;
            }
        }
    }

    public static TreeNode leftist(TreeNode node) {
        if (node == null || node.left == null) {
            return node;
        }
        return leftist(node.left);
    }

    private static void buildParent(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        parentMap.put(root, parent);
        buildParent(root.left, root, parentMap);
        buildParent(root.right, root, parentMap);
    }

}
