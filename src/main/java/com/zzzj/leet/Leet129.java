package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-20 14:53
 */
public class Leet129 {

    public static void main(String[] args) {
        System.out.println(sumNumbers(TreeNode.buildTree("[1,2,3]")));
    }

    private static int result;

    private static void dfs(TreeNode root, StringBuilder path) {
        if (root.left == null && root.right == null) {
            path.append(root.val);
            result += Integer.parseInt(path.toString());
            return;
        }

        if (root.left != null) {
            StringBuilder copy = new StringBuilder(path);
            copy.append(root.val);
            dfs(root.left, copy);
        }

        if (root.right != null) {
            StringBuilder copy = new StringBuilder(path);
            copy.append(root.val);
            dfs(root.right, copy);
        }

    }

    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        result = 0;

        dfs(root, new StringBuilder());

        return result;
    }

}
