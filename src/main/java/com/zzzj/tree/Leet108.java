package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-08 17:22
 */
public class Leet108 {

    public static void main(String[] args) {
        TreeNode node = sortedArrayToBST(new int[]{1, 3});
        System.out.println(node.serialize());
    }

    private static TreeNode dfs(int[] nums, int i, int j) {
        if (i > j) {
            return null;
        }
        int val;
        if (i == j) {
            val = nums[i];
            TreeNode node = new TreeNode(val);
            return node;
        } else {
            val = nums[(i + j) / 2];
        }

        int sub = (i + j) / 2;

        TreeNode node = new TreeNode(val);

        node.left = dfs(nums, i, sub - 1);

        node.right = dfs(nums, sub + 1, j);

        return node;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }


}
