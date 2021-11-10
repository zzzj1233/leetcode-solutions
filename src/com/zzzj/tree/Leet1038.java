package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-10 16:39
 */
public class Leet1038 {

    private static TreeNode previous;

    public static TreeNode bstToGst(TreeNode root) {
        if (root == null){
            return null;
        }
        previous = null;
        postOrder(root);
        System.out.println(root.serialize());
        return root;
    }

    private static void postOrder(TreeNode node) {
        if (node.right != null) {
            postOrder(node.right);
        }
        if (previous == null) {
            previous = node;
        } else {
            node.val += previous.val;
            previous = node;
        }

        if (node.left != null) {
            postOrder(node.left);
        }

    }

    public static void main(String[] args) {
        bstToGst(TreeNode.buildTree("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]"));
    }

}
