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
        List<Integer> list = new ArrayList<>();

        inOrder(root, list);

        int value = -1;

        int N = list.size();
        if (list.isEmpty() || N == 1) {
            return;
        }

        System.out.println(list);

        if (list.get(0) > list.get(1)) {
            swap(root, root.left == null ? root.right : root.left);
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            if (list.get(i - 1) > list.get(i) && list.get(i + 1) < list.get(i)) {
                recover(root, list.get(i));
                return;
            }
        }

    }

    public static void recover(TreeNode root, int value) {
        if (root.val == value) {
            swap(root.left, root.right);
            return;
        }
        if (root.left != null) {
            recover(root.left, value);
        }
        if (root.right != null) {
            recover(root.right, value);
        }
    }

    public static void swap(TreeNode node1, TreeNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public static void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            inOrder(root.left, list);
        }

        list.add(root.val);

        if (root.right != null) {
            inOrder(root.right, list);
        }
    }


}
