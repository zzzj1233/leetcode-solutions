package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-10 20:28
 */
public class Leet1430 {

    public static boolean isValidSequence(TreeNode root, int[] arr) {
        return isValidSequence(root, arr, 0);
    }

    public static boolean isValidSequence(TreeNode root, int[] arr, int index) {
        if (root == null || root.val != arr[index]) {
            return false;
        }
        if (index == arr.length - 1) {
            return root.left == null && root.right == null;
        }
        return isValidSequence(root.left, arr, index + 1) || isValidSequence(root.right, arr, index + 1);
    }

}
