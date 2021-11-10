package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-10 16:07
 */
public class Leet513 {

    private static int answer;

    private static int curLevel;

    public static void main(String[] args) {
        System.out.println(findBottomLeftValue(TreeNode.buildTree("[2,1,3]")));
    }

    public static int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return answer;
    }

    private static void dfs(TreeNode root, int level) {
        if (root.left != null) {
            dfs(root.left, level + 1);
        }

        if (level > curLevel) {
            curLevel = level;
            answer = root.val;
        }

        if (root.right != null) {
            dfs(root.right, level + 1);
        }
    }

}
