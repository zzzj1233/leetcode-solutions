package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.PriorityQueue;

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
        return dfs(nums, 0, nums.length - 1);
    }

    public static int findMax(int[] nums, int left, int right) {
        int maxIndex = left++;
        while (left <= right) {
            if (nums[left] > nums[maxIndex]) {
                maxIndex = left;
            }
            left++;
        }
        return maxIndex;
    }

    public static TreeNode dfs(int[] nums, int left, int right) {
        if (right > left) {
            return null;
        }

        int maxIndex = findMax(nums, left, right);
        TreeNode node = new TreeNode(nums[maxIndex]);

        node.left = dfs(nums, left, maxIndex - 1);
        node.right = dfs(nums, maxIndex + 1, right);

        return node;
    }

}
