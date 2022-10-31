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
        if (root == null) {
            return;
        }

        ArrayList<TreeNode> list = new ArrayList<>();

        inOrder(root, list);

        // 找到两个节点

        TreeNode nodeA = null;
        TreeNode nodeB = null;

        for (int i = 0; i < list.size(); i++) {
            TreeNode cur = list.get(i);
            TreeNode prev = i - 1 >= 0 ? list.get(i - 1) : null;
            TreeNode next = i + 1 < list.size() ? list.get(i + 1) : null;
            if (prev != null && cur.val < prev.val) {
                if (nodeA == null) {
                    nodeA = cur;
                } else {
                    nodeB = cur;
                }
            } else if (next != null && cur.val > next.val) {
                if (nodeA == null) {
                    nodeA = cur;
                } else {
                    nodeB = cur;
                }
            }
        }

        int temp = nodeA.val;
        nodeA.val = nodeB.val;
        nodeB.val = temp;
    }

    public static void inOrder(TreeNode root, ArrayList<TreeNode> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }

}
