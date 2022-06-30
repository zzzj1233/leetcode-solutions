package com.zzzj.daily;

import com.zzzj.leet.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-06-24 18:30
 */
public class Leet515 {

    private static Map<Integer, Integer> maxMap;

    public static List<Integer> largestValues(TreeNode root) {
        maxMap = new HashMap<>();
        dfs(root, 0);
        return new ArrayList<>(maxMap.values());
    }

    public static void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (maxMap.get(level) == null || root.val > maxMap.get(level)) {
            maxMap.put(level, root.val);
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

}
