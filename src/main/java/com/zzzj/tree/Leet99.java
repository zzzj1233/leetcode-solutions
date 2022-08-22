package com.zzzj.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-11-10 10:42
 */
public class Leet99 {

    public static void main(String[] args) {
        recoverTree(TreeNode.buildTree("[3,1,4,null,null,2]"));
        recoverTree(TreeNode.buildTree("[1,3,null,null,2]"));
        recoverTree(TreeNode.buildTree("[1,2]"));
        recoverTree(TreeNode.buildTree("[2,null,1]"));
    }


    public static void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();

        inOrder(root, list);

        int size = list.size();

        // 3 2 1
        // 1 3 2 4
        for (int i = 1; i < size; i++) {
            if (list.get(i).val < list.get(i - 1).val) {
                TreeNode node = list.get(i);
                TreeNode pre = list.get(i - 1);

                return;
            }
        }
    }

    public static void inOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            inOrder(root.left, list);
        }

        list.add(root);

        if (root.right != null) {
            inOrder(root.right, list);
        }
    }


}
