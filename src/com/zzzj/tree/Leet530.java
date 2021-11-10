package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-09 18:33
 */
public class Leet530 {

    public static void main(String[] args) {
        System.out.println(getMinimumDifference(TreeNode.buildTree("[1,0,48,null,null,12,49]")));
    }

    private static class Info {
        public int min;
        public int max;
        public int minDiff;
    }

    public static Info dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);

        Info info = new Info();

        int min = root.val;
        int max = root.val;
        int leftMinDiff = Integer.MAX_VALUE;
        int rightMinDiff = Integer.MAX_VALUE;

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            leftMinDiff = leftInfo.minDiff;
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            rightMinDiff = rightInfo.minDiff;
        }

        info.min = min;
        info.max = max;

        // 1. 当前值减去左边节点的最大值
        int val1 = leftInfo == null ? Integer.MAX_VALUE : root.val - leftInfo.max;
        // 2. 当前值减去右边节点的最小值
        int val2 = rightInfo == null ? Integer.MAX_VALUE : rightInfo.min - root.val;
        // 3. 左右节点的minDiff
        int val3 = Math.min(leftMinDiff, rightMinDiff);

        info.minDiff = Math.min(val1, Math.min(val2, val3));

        return info;
    }

    public static int getMinimumDifference(TreeNode root) {
        return dfs(root).minDiff;
    }

}
