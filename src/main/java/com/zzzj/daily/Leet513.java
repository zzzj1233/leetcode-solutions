package com.zzzj.daily;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2022-06-22 18:24
 */
public class Leet513 {

    public static int ans;

    public static int maxLevel;

    public static int findBottomLeftValue(TreeNode root) {
        ans = 0;
        maxLevel = 0;
        dfs(root, 1);
        return ans;
    }

    public static void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level > maxLevel) {
            ans = root.val;
            maxLevel = level;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

}
