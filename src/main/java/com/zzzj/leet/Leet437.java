package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-05-18 17:06
 */
public class Leet437 {

    public static Map<Integer, Integer> map = new HashMap<>();

    public static int ans;

    public static void main(String[] args) {
        System.out.println(pathSum(TreeNode.buildTree("[1,-2,-3,1,3,-2,null,-1]"), 2));
    }

    // [1,null,2,null,3,null,4,null,5]
    // 3
    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        map.clear();
        map.put(0, 1);
        ans = 0;

        preOrder(root, targetSum, 0);

        return ans;
    }

    // [1,-2,-3,1,3,-2,null,-1]
    // 2
    public static void preOrder(TreeNode root, int target, int path) {
        int val = root.val;

        path += val;

        int propose = path - target;

        if (map.containsKey(propose)) {
            ans += map.get(propose);
        } else if (val == target) {
            ans++;
        }

        map.put(path, map.getOrDefault(val, 0) + 1);

        if (root.left != null) {
            preOrder(root.left, target, path);
        }

        if (root.right != null) {
            preOrder(root.right, target, path);
        }

        int sub = map.get(path) - 1;
        if (sub == 0) {
            map.remove(path);
        } else {
            map.put(path, sub);
        }
    }

}
