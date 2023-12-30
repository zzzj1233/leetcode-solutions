package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

/**
 * @author zzzj
 * @create 2021-11-11 14:34
 */
public class Leet124 {

    public static void main(String[] args) {
        System.out.println(maxPathSum(TreeNode.buildTree("[5,4,8,11,null,13,4,7,2,null,null,null,1]")));
    }


    static int ans;

    public static int maxPathSum(TreeNode root) {

        ans = Integer.MIN_VALUE;

        dfs(root);

        return ans;
    }

    private static int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int lv = dfs(node.left);

        int rv = dfs(node.right);

        ans = Math.max(
                ans,
                Math.max(
                        Math.max(
                                node.val + lv,
                                node.val + rv
                        ),
                        node.val + lv + rv
                )
        );

        return Math.max(
                0,
                Math.max(
                        node.val,
                        Math.max(node.val + lv, node.val + rv)
                )
        );
    }

}
