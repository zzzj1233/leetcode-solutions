package com.zzzj.review;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2023-03-16 17:08
 */
public class Leet98 {

    public static void main(String[] args) {
        System.out.println(isValidBST(TreeNode.buildTree("[2,1,3]")));
        System.out.println(isValidBST(TreeNode.buildTree("[5,1,4,null,null,3,6]")));
        System.out.println(isValidBST(TreeNode.buildTree("[2,2,2]")));
    }

    static final int[] EMPTY = new int[2];

    static final int MIN = 0;

    static final int MAX = 1;

    static boolean ans;

    public static boolean isValidBST(TreeNode root) {
        ans = true;
        dfs(root);
        return ans;
    }

    public static int[] dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (!ans) {
            return EMPTY;
        }

        int[] left = dfs(root.left);

        int[] right = dfs(root.right);

        if (!ans) {
            return EMPTY;
        }

        if (left != null && left[MAX] >= root.val) {
            ans = false;
            return EMPTY;
        }

        if (right != null && right[MIN] <= root.val) {
            ans = false;
            return EMPTY;
        }

        int[] result = new int[2];

        result[MIN] = Math.min(root.val, Math.min(left == null ? Integer.MAX_VALUE : left[MIN], right == null ? Integer.MAX_VALUE : right[MIN]));

        result[MAX] = Math.max(root.val, Math.max(left == null ? Integer.MIN_VALUE : left[MAX], right == null ? Integer.MIN_VALUE : right[MAX]));

        return result;
    }
}
