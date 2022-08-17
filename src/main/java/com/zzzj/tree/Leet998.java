package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-08-13 16:44
 */
public class Leet998 {


    public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
        List<Integer> values = new ArrayList<>();

        inOrder(root, values);

        values.add(val);

        int[] nums = new int[values.size()];

        for (int i = 0; i < values.size(); i++) {
            nums[i] = values.get(i);
        }

        return constructMaximumBinaryTree(nums);
    }

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
        if (right < left) {
            return null;
        }

        int maxIndex = findMax(nums, left, right);
        TreeNode node = new TreeNode(nums[maxIndex]);

        node.left = dfs(nums, left, maxIndex - 1);
        node.right = dfs(nums, maxIndex + 1, right);

        return node;
    }

    public static void inOrder(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        inOrder(root.left, values);
        values.add(root.val);
        inOrder(root.right, values);
    }

}
