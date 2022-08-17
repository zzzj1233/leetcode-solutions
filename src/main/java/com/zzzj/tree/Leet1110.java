package com.zzzj.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-08-16 19:18
 */
public class Leet1110 {

    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> delete = new HashSet<>(to_delete.length);

        for (int i : to_delete) {
            delete.add(i);
        }

        List<TreeNode> ans = new ArrayList<>();

        TreeNode node = dfs(root, delete, ans);

        if (node != null){
            ans.add(node);
        }

        return ans;
    }

    public static TreeNode dfs(TreeNode root, Set<Integer> delete, List<TreeNode> ans) {
        if (root == null) {
            return null;
        }

        root.left = dfs(root.left, delete, ans);
        root.right = dfs(root.right, delete, ans);

        if (delete.contains(root.val)) {
            if (root.left != null) {
                ans.add(root.left);
            }
            if (root.right != null) {
                ans.add(root.right);
            }
            return null;
        }

        return root;
    }

}
