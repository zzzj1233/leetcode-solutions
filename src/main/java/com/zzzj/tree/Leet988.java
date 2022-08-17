package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 19:34
 */
public class Leet988 {

    public static void main(String[] args) {
        System.out.println(smallestFromLeaf(TreeNode.buildTree("[25,1,null,0,0,1,null,null,null,0]")));
    }

    public static String smallestFromLeaf(TreeNode root) {
        StringBuilder builder = dfs(root);
        return builder == null ? "" : builder.toString();
    }

    public static StringBuilder dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return new StringBuilder(1).append(((char) ('a' + root.val)));
        }

        if (root.left != null && root.right != null) {
            StringBuilder left = dfs(root.left).append(((char) ('a' + root.val)));
            StringBuilder right = dfs(root.right).append(((char) ('a' + root.val)));
            return lt(left, right);
        } else if (root.left != null) {
            StringBuilder left = dfs(root.left);
            return left.append(((char) ('a' + root.val)));
        } else {
            StringBuilder right = dfs(root.right);
            return right.append(((char) ('a' + root.val)));
        }
    }

    public static StringBuilder lt(StringBuilder left, StringBuilder right) {
        int N = Math.min(left.length(), right.length());

        for (int i = 0; i < N; i++) {
            char a = left.charAt(i);
            char b = right.charAt(i);

            if (a < b) {
                return left;
            } else if (b < a) {
                return right;
            }
        }

        return left.length() == N ? right : left;
    }

}
