package com.zzzj.tree;


import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-09 15:37
 */
public class Leet1740 {

    public static void main(String[] args) {
        findDistance(TreeNode.buildTree("[3,5,1,6,2,0,8,null,null,7,4]"), 5, 0);
        findDistance(TreeNode.buildTree("[3,5,1,6,2,0,8,null,null,7,4]"), 5, 7);
        findDistance(TreeNode.buildTree("[3,5,1,6,2,0,8,null,null,7,4]"), 7, 4);
    }

    public static int findDistance(TreeNode root, int p, int q) {
        if (p == q) {
            return 0;
        }

        if (root == null) {
            return 0;
        }

        TreeNode ancestor = lowestCommonAncestor(root, p, q);


        return -1;
    }


    // 寻找公共祖先
    public static TreeNode lowestCommonAncestor(TreeNode node, int p, int q) {
        if (node == null) {
            return null;
        }

        if (node.val == q || node.val == p) {
            return node;
        }

        // 后序遍历
        TreeNode left = lowestCommonAncestor(node.left, p, q);

        TreeNode right = lowestCommonAncestor(node.right, p, q);

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return node;
    }


}
