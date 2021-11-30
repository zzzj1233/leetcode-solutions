package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-11-12 17:32
 */
public class Leet654 {

    public static void main(String[] args) {
        System.out.println(constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}).serialize());
    }

    // 给定一个数组,构造一个最大树
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        return dfs(nums, 0, nums.length - 1);
    }

    private static TreeNode dfs(int[] nums, int i, int j) {
        if (i >= j) {
            return null;
        }

        TreeNode node = new TreeNode(nums[j]);

        int mid = (j + i) / 2 + 1;

        node.left = dfs(nums, 0, mid);
        node.right = dfs(nums, mid + 1, j - 1);

        return node;
    }

}
