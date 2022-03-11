package com.zzzj.backtracking;

import com.zzzj.leet.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-09 14:56
 */
public class Leet2281 {

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.buildTree("[5,4,8,11,null,13,4,7,2,null,null,5,1]"), 22));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(ans, new LinkedList<>(), root, target, 0);

        return ans;
    }

    public static void dfs(List<List<Integer>> ans, LinkedList<Integer> path, TreeNode root, int target, int cur) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (cur + root.val == target && path.size() > 1) {
            ans.add(new ArrayList<>(path));
        }
        dfs(ans, path, root.left, target, cur + root.val);
        dfs(ans, path, root.right, target, cur + root.val);
        path.removeLast();
    }

}
