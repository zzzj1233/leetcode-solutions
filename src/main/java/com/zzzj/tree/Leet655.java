package com.zzzj.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-17 17:35
 */
public class Leet655 {

    public static void main(String[] args) {
        System.out.println(printTree(TreeNode.buildTree("[1,2]")));
    }

    public static List<List<String>> printTree(TreeNode root) {
        int depth = getDepth(root);

        int m = (int) (Math.pow(2, depth) - 1);

        List<List<String>> ans = new ArrayList<>(depth);

        for (int i = 0; i < depth; i++) {
            List<String> subList = new ArrayList<>(m);
            for (int j = 0; j < m; j++) {
                subList.add("");
            }
            ans.add(subList);
        }

        dfs(ans, root, 0, m / 2, depth);

        return ans;
    }

    public static void dfs(List<List<String>> ans, TreeNode root, int row, int col, int depth) {
        if (root == null) {
            return;
        }
        ans.get(row).set(col, String.valueOf(root.val));

        dfs(ans, root.left, row + 1, (int) (col - Math.pow(2, depth - row - 2)), depth);
        dfs(ans, root.right, row + 1, (int) (col + Math.pow(2, depth - row - 2)), depth);
    }

    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}
