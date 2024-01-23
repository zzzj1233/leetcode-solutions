package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2023-12-26 17:40
 */
public class LCP34 {

    public static void main(String[] args) {

//        System.out.println(maxValue(TreeNode.buildTree("[4,1,3,9,null,null,2]"), 2));
//
//        System.out.println(maxValue(TreeNode.buildTree("[5,2,3,4]"), 2));
//
        System.out.println(maxValue(TreeNode.buildTree("[1,6,null,8,8,null,5,3,null,7,10,5,8]"), 10));

    }

    public static int maxValue(TreeNode root, int k) {
        return dfs(root, k)[k];
    }

    static final int[] EMPTY = new int[11];

    private static int[] dfs(
            TreeNode root,
            int k
    ) {

        if (root == null)
            return EMPTY;

        int[] lv = dfs(root.left, k);

        int[] rv = dfs(root.right, k);

        int[] res = new int[k + 1];

        res[0] = lv[k] + rv[k];

        res[1] = Math.max(
                res[0],
                root.val + lv[0] + rv[0]
        );

        for (int i = 1; i < k; i++) {

            for (int j = 1; j <= i; j++) {
                res[i + 1] = Math.max(
                        lv[j] + rv[i - j],
                        rv[j] + lv[i - j]
                );
            }

            res[i + 1] += root.val;

            res[i + 1] = Math.max(
                    res[i + 1],
                    res[i]
            );
        }

        return res;
    }

}
