package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-11-12 12:17
 */
public class Leet437 {

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.buildTree("[0,1,1]"), 1));
    }

    public static int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, targetSum, map, 0);
    }

    public static int dfs(TreeNode root, int targetSum, Map<Integer, Integer> map, int path) {
        if (root == null) {
            return 0;
        }

        int val = root.val;

        Integer ret = map.getOrDefault(path + val - targetSum, 0);

        map.put(path + root.val, map.getOrDefault(path + root.val, 0) + 1);

        ret += dfs(root.left, targetSum, map, path + val);
        ret += dfs(root.right, targetSum, map, path + val);

        map.put(path + root.val, map.getOrDefault(path + root.val, 0) - 1);

        return ret;
    }

}
