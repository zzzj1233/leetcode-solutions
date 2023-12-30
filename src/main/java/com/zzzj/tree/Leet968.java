package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-17 12:03
 */
public class Leet968 {

    public static void main(String[] args) {

        System.out.println(minCameraCover(TreeNode.buildTree("[0,0,null,0,0]")));

        System.out.println(minCameraCover(TreeNode.buildTree("[0,0,null,0,null,0,null,null,0]")));

    }

    public static int minCameraCover(TreeNode root) {
        if (root == null)
            return 0;

        int[] f = dfs(root);

        return Math.min(
                f[1],
                f[2]
        );
    }

    // 0 = 父节点照亮自己
    // 1 = 子节点照亮自己
    // 2 = 自己照亮自己
    private static int[] dfs(TreeNode root) {

        if (root.left == null && root.right == null) {
            return new int[]{0, Integer.MAX_VALUE, 1};
        }

        if (root.left == null) {
            int[] r = dfs(root.right);
            return new int[]{
                    Math.min(r[1], r[2]),
                    r[2],
                    1 + Math.min(r[0], Math.min(r[1], r[2]))};
        }

        if (root.right == null) {
            int[] l = dfs(root.left);
            return new int[]{
                    Math.min(l[1], l[2]),
                    l[2],
                    1 + Math.min(l[0], Math.min(l[1], l[2]))
            };
        }

        int[] r = dfs(root.right);
        int[] l = dfs(root.left);

        return new int[]{
                Math.min(r[1], r[2]) + Math.min(l[1], l[2]),
                // 左边照亮我
                Math.min(
                        l[2] + Math.min(r[1], r[2]),
                        r[2] + Math.min(l[1], l[2])
                ),
                1 + Math.min(r[0],
                        Math.min(r[1], r[2])
                ) + Math.min(l[0],
                        Math.min(l[1], l[2])
                )
        };
    }

}



