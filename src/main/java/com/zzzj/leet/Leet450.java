package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-19 16:14
 */
public class Leet450 {

    public static void main(String[] args) {

        final TreeNode node = deleteNode(TreeNode.buildTree("[5,3,6,2,4,null,7]"), 3);

        System.out.println(node.serialize());
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // 删除搜索二叉树的节点
        return removeNode(root, key);
    }

    // [5,3,6,2,4,null,7]
    // 3
    public static int findLeftest(TreeNode root) {
        if (root.left == null) {
            return root.val;
        }
        return findLeftest(root.left);
    }

    public static TreeNode removeNode(TreeNode root, int key) {
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            int leftest = findLeftest(root.right);
            root.right = removeNode(root.right, leftest);
            root.val = leftest;
            return root;
        }
        if (key < root.val && root.left != null) {
            root.left = removeNode(root.left, key);
        }
        if (key > root.val && root.right != null) {
            root.right = removeNode(root.right, key);
        }
        return root;
    }

}
