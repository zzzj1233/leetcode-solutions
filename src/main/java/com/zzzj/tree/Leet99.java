package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-11-10 10:42
 */
public class Leet99 {

    public static void main(String[] args) {
        TreeNode tree = TreeNode.buildTree("[1,3,null,null,2]");

        recoverTree(tree);

        System.out.println(tree.serialize());
    }

    private static List<TreeNode> values;

    public static void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        values = new ArrayList<>();

        inOrder(root);

        for (int i = 0; i < values.size() - 1; i++) {
            TreeNode next = values.get(i + 1);
            TreeNode cur = values.get(i);
            if (cur.val > next.val) {

            }
        }

    }

    private static void inOrder(TreeNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        values.add(root);
        if (root.right != null) {
            inOrder(root.right);
        }
    }


}
