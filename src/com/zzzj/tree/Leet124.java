package com.zzzj.tree;

import com.zzzj.leet.TreeNode;
import com.zzzj.util.Unresolved;

/**
 * @author zzzj
 * @create 2021-11-11 14:34
 */
@Unresolved
public class Leet124 {

    public static void main(String[] args) {
        System.out.println(maxPathSum(TreeNode.buildTree("[5,4,8,11,null,13,4,7,2,null,null,null,1]")));
    }

    private static class Info {
        public int total;
        public int leftVal;
        public int rightVal;
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root).total;
    }

    private static Info dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);

        Info info = new Info();
        info.leftVal = root.val + (leftInfo == null ? 0 : leftInfo.total);
        info.rightVal = root.val + (rightInfo == null ? 0 : rightInfo.total);

        int leftMax = leftInfo == null ? 0 : leftInfo.total;

        int rightMax = rightInfo == null ? 0 : rightInfo.total;

        int max1 = root.val + leftMax;

        int max2 = root.val + rightMax;

        int max3 = root.val + leftMax + rightMax;

        int max4 = root.val;

        int max = Math.max(Math.max(max1, max4), Math.max(max2, max3));

        if (leftInfo != null && rightInfo != null) {
            info.total = Math.max(Math.max(leftInfo.total, rightInfo.total), max);
        } else if (leftInfo != null) {
            info.total = Math.max(leftInfo.total, max);
        } else if (rightInfo != null) {
            info.total = Math.max(rightInfo.total, max);
        } else {
            info.total = max;
        }

        return info;
    }

}
