package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-20 10:21
 */
public class Leet112 {


    public static void main(String[] args) {
        System.out.println(hasPathSum(TreeNode.buildTree("[1,2,3]"), 5));
    }

    private static boolean has = false;

    public static void hasPathSum(TreeNode root, int cur, int tar) {
        if (root.left == null && root.right == null) {
            if (cur + root.val == tar) {
                has = true;
            }
        } else if (!has) {
            if (root.left != null) {
                hasPathSum(root.left, cur + root.val, tar);
            }

            if (root.right != null) {
                hasPathSum(root.right, cur + root.val, tar);
            }
        }
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        has = false;

        hasPathSum(root, 0, targetSum);

        return has;
    }

}
