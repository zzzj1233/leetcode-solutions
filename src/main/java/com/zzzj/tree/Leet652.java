package com.zzzj.tree;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-11 12:03
 */
public class Leet652 {

    static LinkedList<TreeNode> ans;

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        ans = new LinkedList<>();
        dfs(root, new HashMap<>());
        return ans;
    }

    public static String dfs(TreeNode root, Map<String, Integer> map) {
        if (root == null) {
            return "";
        }
        String str = root.val + "#" + dfs(root.left, map) + "*" + dfs(root.right, map);
        Integer count = map.getOrDefault(str, 0);
        if (count == 1) {
            ans.addFirst(root);
        }
        map.put(str, count + 1);
        return str;
    }

}
