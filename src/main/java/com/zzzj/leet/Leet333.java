package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-25 11:30
 */
public class Leet333 {

    private static class Info {
        boolean isBst;
        int node;
        int min;
        int max;

        public Info(boolean isBst, int node) {
            this.isBst = isBst;
            this.node = node;
        }

        public Info(boolean isBst, int node, int min, int max) {
            this.isBst = isBst;
            this.node = node;
            this.min = min;
            this.max = max;
        }

        public Info() {
        }
    }

    private static Info dfs(TreeNode root) {
        if (root == null) {
            return new Info(false, 0);
        }

        final int value = root.val;

        if (root.left == null && root.right == null) {
            return new Info(true, 1, value, value);
        }

        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);

        Info info = new Info();


        if (root.left != null && root.right != null) {
            if (leftInfo.isBst && rightInfo.isBst) {
                if (value > leftInfo.max && value < rightInfo.min) {
                    info.node = leftInfo.node + rightInfo.node + 1;
                    info.isBst = true;
                    info.min = leftInfo.min;
                    info.max = rightInfo.max;
                }
            }
        } else if (root.left != null) { // right == null
            if (leftInfo.isBst && value > leftInfo.max) {
                info.node = leftInfo.node + 1;
                info.isBst = true;
                info.min = leftInfo.min;
                info.max = value;
            }
        } else if (rightInfo.isBst && value < rightInfo.min) {
            info.node = rightInfo.node + 1;
            info.isBst = true;
            info.min = value;
            info.max = rightInfo.max;
        }

        info.node = Math.max(info.node, Math.max(leftInfo.node, rightInfo.node));

        return info;
    }

    public static int largestBSTSubtree(TreeNode root) {
        return dfs(root).node;
    }

    public static void main(String[] args) {
        System.out.println(largestBSTSubtree(TreeNode.buildTree("[10,5,15,1,8,null,7]")));
    }

}