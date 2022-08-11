package com.zzzj.tree;

import javax.swing.*;

/**
 * @author zzzj
 * @create 2022-08-09 18:15
 */
public class Leet1120 {


    public static void main(String[] args) {
        System.out.println(maximumAverageSubtree(TreeNode.buildTree("[0,null,1]")));
    }

    static double ans;

    public static double maximumAverageSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ans = 0;
        dfs(root);
        return ans;
    }

    static double[] EMPTY = new double[2];

    public static double[] dfs(TreeNode node) {
        if (node == null) {
            return EMPTY;
        }

        double[] left = dfs(node.left);
        double[] right = dfs(node.right);

        double sum = left[0] + right[0] + node.val;
        double count = left[1] + right[1] + 1;

        ans = Math.max(ans, sum / count);

        return new double[]{sum, count};
    }


}
