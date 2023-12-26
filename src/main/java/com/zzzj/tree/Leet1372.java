package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 18:53
 */
public class Leet1372 {

    public static void main(String[] args) {
//        System.out.println(longestZigZag(TreeNode.buildTree("[1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]")));

        System.out.println(longestZigZag(TreeNode.buildTree("[1,1,1,null,1,null,null,1,1,null,1]")));
    }

    static int ans;

    public static int longestZigZag(TreeNode root) {

        ans = 0;

        dfs(root);

        return ans;
    }

    private static int[] dfs(TreeNode root) {

        if (root == null)
            return new int[]{0, 0};

        int[] lv = dfs(root.left);
        int[] rv = dfs(root.right);

        int[] res = new int[2];

        res[0] = lv[0] + 1;
        res[1] = rv[1] + 1;

        ans = Math.max(ans, Math.max(res[0], res[1]));

        return res;
    }

}
