package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-11 18:57
 */
public class Leet663 {

    public static void main(String[] args) {
        System.out.println(checkEqualTree(TreeNode.buildTree("[5,10,10,null,null,2,3]")));
    }

    static Map<Integer, Integer> sumMap;

    public static boolean checkEqualTree(TreeNode root) {

        sumMap = new HashMap<>();

        int sum = sum(root);

        if (sum % 2 != 0) {
            return false;
        }

        if (sum == 0){
            return sumMap.get(0) > 1;
        }

        return sumMap.containsKey(sum / 2);
    }

    private static int sum(TreeNode root) {
        if (root == null){
            return 0;
        }

        int sum = root.val + sum(root.left) + sum(root.right);

        sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);

        return sum;
    }


}
