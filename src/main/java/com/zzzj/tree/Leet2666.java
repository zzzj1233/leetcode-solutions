package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-17 17:49
 */
public class Leet2666 {

    public static int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        return dfs(root, sum, map, 0);
    }

    public static int dfs(TreeNode root, int sum, Map<Integer, Integer> map, int path) {
        if (root == null) {
            return 0;
        }
        int cur = path + root.val;

        int ret = map.getOrDefault(cur - sum, 0);

        map.put(cur, map.getOrDefault(cur, 0) + 1);

        ret += dfs(root.left, sum, map, cur);
        ret += dfs(root.right, sum, map, cur);

        map.put(cur, map.get(cur) - 1);

        return ret;
    }

}
