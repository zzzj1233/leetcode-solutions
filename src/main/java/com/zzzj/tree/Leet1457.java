package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 12:09
 */
public class Leet1457 {

    public static int pseudoPalindromicPaths(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int path) {
        // 当前节点是leaf
        if (root.left == null && root.right == null) {
            path ^= (1 << root.val);

            // 只允许有一位是1
            boolean allow = true;

            for (int i = 0; i < 32; i++) {
                if (((path >> i) & 1) == 1) {
                    if (!allow) {
                        return 0;
                    }
                    allow = false;
                }
            }

            // 从root到leaf, 满足只有一位是1, 当前路径是一条伪回文路径
            return 1;
        }

        int result = 0;

        if (root.left != null) {
            result += dfs(root.left, (path ^ (1 << root.val)));
        }

        if (root.right != null) {
            result += dfs(root.right, (path ^ (1 << root.val)));
        }

        return result;
    }


}
