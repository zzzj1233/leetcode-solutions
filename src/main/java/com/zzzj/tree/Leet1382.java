package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-08-13 18:56
 */
public class Leet1382 {

    public static void main(String[] args) {
        System.out.println(balanceBST(TreeNode.buildTree("[1,null,2,null,3,null,4,null,null]")).serialize());
    }

    public static TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        inOrder(root, list);

        return buildBalanceBST(list, 0, list.size() - 1);
    }

    public static TreeNode buildBalanceBST(List<Integer> values, int left, int right) {
        if (left == right) {
            return new TreeNode(values.get(left));
        }

        if (left > right) {
            return null;
        }

        int mid = left + ((right - left) >> 1);

        TreeNode node = new TreeNode(values.get(mid));

        node.left = buildBalanceBST(values, left, mid - 1);
        node.right = buildBalanceBST(values, mid + 1, right);

        return node;
    }

    public static void inOrder(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }

        inOrder(root.left, values);
        values.add(root.val);
        inOrder(root.right, values);
    }

}
