package com.zzzj.leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-20 14:17
 */
public class Leet113 {

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.buildTree("[5,4,8,11,null,13,4,7,2,null,null,5,1]"), 22));
    }

    private static List<List<Integer>> ans;

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        ans = new ArrayList<>();

        dfs(root, target, new LinkedList<>(), 0);

        return ans;
    }

    public static void dfs(TreeNode root, int target, LinkedList<Integer> path, int cur) {
        if (root == null) {
            return;
        }
        path.add(root.val);

        if (root.left == null && root.right == null) {
            if (cur + root.val == target) {
                ans.add(new ArrayList<>(path));
            }
        }

        dfs(root.left, target, path, cur + root.val);
        dfs(root.right, target, path, cur + root.val);

        path.removeLast();
    }


}