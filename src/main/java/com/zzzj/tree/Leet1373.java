package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-31 15:27
 */
public class Leet1373 {

    public static void main(String[] args) {
        System.out.println(maxSumBST(TreeNode.buildTree("[1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]")));
    }

    public static int maxSumBST(TreeNode root) {
        ans = 0;

        dfs(root);

        return ans;
    }

    // 0 - max
    // 1 - min
    // 2 - sum

    static final int[] EMPTY = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};

    static int ans;

    private static int[] dfs(TreeNode root) {

        if (root == null)
            return EMPTY;

        int[] lv = dfs(root.left);

        int[] rv = dfs(root.right);

        int v = root.val;

        if (v > lv[0] && v < rv[1]) {
            ans = Math.max(ans, v + lv[2] + rv[2]);
            return new int[]{
                    Math.max(v, Math.max(rv[0], lv[0])),
                    Math.min(v, Math.min(rv[1], lv[1])),
                    v + lv[2] + rv[2]
            };
        }

        return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
    }

}
