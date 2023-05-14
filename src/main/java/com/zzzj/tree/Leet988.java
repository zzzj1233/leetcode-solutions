package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 19:34
 */
public class Leet988 {

    public static void main(String[] args) {
        System.out.println(smallestFromLeaf(TreeNode.buildTree("[25,1,null,0,0,1,null,null,null,0]")));
    }

    static String ans = null;

    public static String smallestFromLeaf(TreeNode root) {
        ans = null;
        dfs(root, new StringBuilder());
        return ans;
    }

    public static void dfs(TreeNode node, StringBuilder builder) {
        if (node == null) {
            return;
        }

        builder.append((char) (node.val + 'a'));

        if (node.left == null && node.right == null) {
            String str = new StringBuilder(builder.toString()).reverse().toString();
            if (ans == null || str.compareTo(ans) < 0) {
                ans = str;
            }
        }

        dfs(node.left, builder);
        dfs(node.right, builder);
        builder.setLength(builder.length() - 1);
    }


}
