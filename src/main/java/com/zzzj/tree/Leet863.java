package com.zzzj.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-08-13 22:12
 */
public class Leet863 {

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        dfs(root, null, parentMap);

        List<Integer> ans = new ArrayList<>();

        boolean[] visited = new boolean[501];

        collect(target, k, 0, ans, parentMap, visited);

        return ans;
    }

    public static void collect(TreeNode root, int k, int distance, List<Integer> list, Map<TreeNode, TreeNode> parentMap, boolean[] visited) {
        if (root == null) {
            return;
        }
        if (visited[root.val]) {
            return;
        }
        visited[root.val] = true;
        if (k == distance) {
            list.add(root.val);
            return;
        }
        collect(parentMap.get(root), k, distance + 1, list, parentMap, visited);
        collect(root.left, k, distance + 1, list, parentMap, visited);
        collect(root.right, k, distance + 1, list, parentMap, visited);
    }


    public static void dfs(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        parentMap.put(root, parent);
        dfs(root.left, root, parentMap);
        dfs(root.right, root, parentMap);
    }

}
