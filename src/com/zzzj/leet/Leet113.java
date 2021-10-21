package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-10-20 14:17
 */
public class Leet113 {

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.buildTree("[5,4,8,11,null,13,4,7,2,null,null,5,1]"), 22));
    }

    private static List<List<Integer>> res;

    private static void dfs(TreeNode root, int cur, int tar, LinkedList<Integer> path) {
        // leaf
        if (root.left == null && root.right == null) {
            if (root.val + cur == tar) {
                path.add(root.val);
                res.add(path);
            }
        }

        if (root.left != null) {
            path.add(root.val);
            dfs(root.left, cur + root.val, tar, path);
            path.remove(root.val);
        }

        if (root.right != null) {
            path.add(root.val);
            dfs(root.right, cur + root.val, tar, path);
            path.remove(root.val);
        }

    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return Collections.emptyList();
        }

        res = new ArrayList<>();

        dfs(root, 0, targetSum, new LinkedList<>());

        return res;
    }

}