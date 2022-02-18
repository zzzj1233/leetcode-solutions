package com.zzzj.dp;

import com.zzzj.leet.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-10-29 12:27
 */
public class Leet337 {

    /*
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \.
     *
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     *
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     */
    public static void main(String[] args) {
        System.out.println(rob(TreeNode.buildTree("[3,4,5,1,3,null,1]")));
    }

    private static int[] dp(TreeNode root) {
        return null;
    }

    private static Map<TreeNode, Integer> cache;

    public static int rob(TreeNode root) {
        cache = new HashMap<>();
        // 偷了当前节点,就无法偷左节点和右节点
        return dfs(root);
    }

    private static int dfs(TreeNode root) {
        Integer cached = cache.get(root);

        if (cached != null) {
            return cached;
        }

        if (root == null) {
            return 0;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        int val1 = root.val;

        if (left != null) {
            val1 += dfs(left.left);
            val1 += dfs(left.right);
        }

        if (right != null) {
            val1 += dfs(right.left);
            val1 += dfs(right.right);
        }

        int val2 = dfs(left) + dfs(right);

        int val = Math.max(val1, val2);

        cache.put(root, val);

        return val;
    }

}
