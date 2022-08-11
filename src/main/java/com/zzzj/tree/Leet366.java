package com.zzzj.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zzzj
 * @create 2022-08-07 23:00
 */
public class Leet366 {

    public static void main(String[] args) {
        System.out.println(findLeaves(TreeNode.buildTree("[1,2,3,4,5]")));
    }

    public static void postOrder(TreeNode root, List<List<Integer>> ans, AtomicInteger level) {
        if (root.left == null && root.right == null) {
            level.set(0);
            ans.get(0).add(root.val);
            return;
        }

        int curLevel = 0;

        if (root.left != null) {
            postOrder(root.left, ans, level);
            curLevel = level.get() + 1;
        }

        if (root.right != null) {
            postOrder(root.right, ans, level);
            curLevel = Math.max(curLevel, level.get() + 1);
        }

        ans.get(curLevel).add(root.val);
        level.set(curLevel);
    }

    public static int getMaxLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getMaxLevel(root.left), getMaxLevel(root.right)) + 1;
    }


    public static List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        int max = getMaxLevel(root);


        List<List<Integer>> ans = new ArrayList<>(max);

        for (int i = 0; i < max; i++) {
            ans.add(new ArrayList<>());
        }

        postOrder(root, ans, new AtomicInteger());

        return ans;
    }


}
