package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-12 11:52
 */
public class Leet222 {

    public static void main(String[] args) {
        // [1,2,3,4,5,6,7]
        TreeNode root = TreeNode.buildTree("[1,2,3,4,5,6]");
        System.out.println(countNodes(root));
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = leftestHeight(root.left);
        int rightHeight = leftestHeight(root.right);

        // 左树是满的
        if (leftHeight == rightHeight) {
            // 1 = root节点
            // 1 << height - 1 = 左树的节点个数 相当于 Math.pow(2, leftHeight) - 1
            return 1 + ((1 << leftHeight) - 1) + countNodes(root.right);
        } else { // 右树是满的,但是右树肯定比左树少一层

            // (1 << (leftHeight - 1)) - 1 = 右树的节点个数 相当于 Math.pow(2, leftHeight - 1) - 1
            return 1 + ((1 << (leftHeight - 1)) - 1) + countNodes(root.left);
        }
    }

    public static int leftestHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + leftestHeight(node.left);
    }

}
