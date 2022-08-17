package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-15 14:59
 */
public class Leet1339 {

    public static void main(String[] args) {
        System.out.println(maxProduct(TreeNode.buildTree("[1,null,2,3,4,null,null,5,6]")));
    }

    static long ans;

    static Map<TreeNode, Long> leftSum;

    static Map<TreeNode, Long> rightSum;

    public static int maxProduct(TreeNode root) {
        ans = 0;
        leftSum = new HashMap<>();
        rightSum = new HashMap<>();

        long sum = sum(root);

        dfs(root, sum);

        return (int) (ans % (Math.pow(10, 9) + 7));
    }

    public static void dfs(TreeNode root, long sum) {
        if (root == null) {
            return;
        }

        Long left = leftSum.get(root);
        Long right = rightSum.get(root);

        ans = Math.max(ans, Math.max(left * (sum - left), right * (sum - right)));

        dfs(root.left, sum);
        dfs(root.right, sum);
    }

    public static long sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long left = sum(root.left);
        long right = sum(root.right);

        leftSum.put(root, left);
        rightSum.put(root, right);

        return root.val + left + right;
    }

}
