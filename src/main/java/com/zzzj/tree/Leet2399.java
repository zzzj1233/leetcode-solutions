package com.zzzj.tree;


/**
 * @author zzzj
 * @create 2022-04-01 17:14
 */
public class Leet2399 {

    public static void main(String[] args) {
        System.out.println(convertBST(TreeNode.buildTree("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]")).serialize());
    }

    public static int sum;

    public static TreeNode convertBST(TreeNode root) {
        sum = 0;
        postOrder(root);
        return root;
    }

    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.right);
        sum += root.val;
        root.val = sum;
        postOrder(root.left);
    }

}
