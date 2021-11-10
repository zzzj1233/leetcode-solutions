package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-11-10 15:16
 */
public class Leet99 {

    private static List<TreeNode> list;

    public static void main(String[] args) {
        TreeNode tree = TreeNode.buildTree("[3,1,4,null,null,2]");
        recoverTree(tree);
        System.out.println(tree.serialize());
    }

    public static void recoverTree(TreeNode root) {
        solution1(root);
    }

    public static void solution1(TreeNode root) {
        list = new ArrayList<>();
        inOrder(root);

        int k = -1;

        for (int i = 0; i < list.size(); i++) {
            int curVal = list.get(i).val;

            if ((i - 1 >= 0 && curVal < list.get(i - 1).val) || (i + 1 < list.size() && curVal > list.get(i + 1).val)) {
                if (k == -1) {
                    k = i;
                } else {
                    TreeNode temp = list.get(k);
                    list.set(k, list.get(i));
                    list.set(i, temp);
                }
            }

        }

    }

    private static void inOrder(TreeNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }

        list.add(root);

        if (root.right != null) {
            inOrder(root.right);
        }

    }


}
