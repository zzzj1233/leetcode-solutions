package com.zzzj.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-08-07 23:29
 */
public class Leet652 {


    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<TreeNode> ans = new ArrayList<>();

        return null;
    }

    public static void dfs(TreeNode root, Map<Integer, TreeNode> map) {
        if (root.left != null) {
            dfs(root.left, map);
        }

        if (root.right != null) {
            dfs(root.right, map);
        }

        

        map.put(root.val, root);
    }

}
