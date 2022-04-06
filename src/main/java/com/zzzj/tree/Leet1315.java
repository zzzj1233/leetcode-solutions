package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-04-01 17:26
 */
public class Leet1315 {

    public static int ans;

    public static int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ans = 0;
        dfs(root);
        return ans;
    }

    public static void dfs(TreeNode root) {
        if (root.val % 2 == 0) {
            // left#left,left#right
            if (root.left != null) {
                if (root.left.left != null) {
                    ans += root.left.left.val;
                }
                if (root.left.right != null) {
                    ans += root.left.right.val;
                }
            }
            // right#left,right#left
            if (root.right != null) {
                if (root.right.left != null) {
                    ans += root.right.left.val;
                }
                if (root.right.right != null) {
                    ans += root.right.right.val;
                }
            }
        }

        if (root.left != null) {
            dfs(root.left);
        }

        if (root.right != null) {
            dfs(root.right);
        }
    }

}
