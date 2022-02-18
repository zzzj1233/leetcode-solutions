package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-12 16:08
 */
public class Leet979 {

    public static void main(String[] args) {
        System.out.println(distributeCoins(TreeNode.buildTree("[3,0,0]")));
    }

    private static int step = 0;

    public static int distributeCoins(TreeNode root) {
        step = 0;
        inOrder(root);
        return step;
    }

    private static int inOrder(TreeNode root) {
        int leftRet = 0, rightRet = 0;

        if (root.left != null) {
            leftRet = inOrder(root.left);
        }

        if (root.right != null) {
            rightRet = inOrder(root.right);
        }

        root.val += leftRet + rightRet;

        // 没有就借,有就分
        int val = root.val;

        if (root.val > 1) {
            int sub = val - 1;
            step += sub;
            root.val -= sub;
            return sub;
        } else if (root.val == 1) {
            return 0;
        } else {
            step += Math.abs(val - 1);
            root.val = 1;
            return val - 1;
        }

    }

}
