package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-08 17:22
 */
public class Leet108 {

    public static void main(String[] args) {
        // TreeNode node = sortedArrayToBST(new int[]{1, 3});
        System.out.println(sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    // 有序数组转BST
    private static TreeNode dfs(int[] nums, int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return new TreeNode(nums[i]);
        }

        int mid = (j + i) / 2;

        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, i, mid - 1);
        node.right = dfs(nums, mid + 1, j);
        return node;
    }


}
