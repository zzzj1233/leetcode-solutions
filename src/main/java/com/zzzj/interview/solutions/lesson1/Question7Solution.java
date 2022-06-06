package com.zzzj.interview.solutions.lesson1;

import com.zzzj.leet.LeetUtils;
import com.zzzj.leet.TreeNode;

/**
 * @author Zzzj
 * @create 2022-06-06 23:11
 */
public class Question7Solution {

    public static void main(String[] args) {
        System.out.println(LeetUtils.randomTree(100, 0, 50).serialize());
    }

    public static int solute(TreeNode root) {
        return dfs(root);
    }


    public static int dfs(TreeNode root) {
        /**
         *      -3
         * null    -5
         *
         * 所以必须以root.left == null && root.right == null 作为 base case
         */
        if (root.left == null && root.right == null) {
            return root.val;
        }

        if (root.left == null) {
            return dfs(root.right) + root.val;
        }

        if (root.right == null) {
            return dfs(root.left) + root.val;
        }

        return Math.max(dfs(root.left), dfs(root.right)) + root.val;
    }

}
