package com.zzzj.tree;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-01 17:22
 */
public class Leet1469 {

    public static List<Integer> ans;

    public static List<Integer> getLonelyNodes(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        ans = new ArrayList<>();

        dfs(root);

        return ans;
    }

    public static void dfs(TreeNode root) {
        if (root.left != null || root.right == null) {
            if (root.left != null) {
                dfs(root.left);
                if (root.right == null) {
                    ans.add(root.left.val);
                } else {
                    dfs(root.right);
                }
            }
        } else {
            dfs(root.right);
            ans.add(root.right.val);
        }
    }

}
