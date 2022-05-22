package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-05-18 17:06
 */
public class Leet437 {

    public static Map<Integer, Integer> map;

    public static int ans;

    public static int sum;

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.buildTree("[10,5,-3,3,2,null,11,3,-2,null,1]"), 8));
    }


    public static int pathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return 0;
        }

        map = new HashMap<>();
        ans = 0;
        sum = 0;

        preOrder(root, targetSum);

        return ans;
    }

    public static void preOrder(TreeNode root, int targetSum) {
        sum += root.val;

        ans += map.getOrDefault(sum - targetSum, 0);

        map.put(sum, map.getOrDefault(sum, 0) + 1);

        if (root.left != null) {
            preOrder(root.left, targetSum);
        }

        if (root.right != null) {
            preOrder(root.right, targetSum);
        }

        map.put(sum, map.get(sum) - 1);

        sum -= root.val;
    }


}
