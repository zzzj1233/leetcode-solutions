package com.zzzj.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-08-15 17:58
 */
public class Leet2196 {

    public static TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, int[]> parentMap = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int i = 0; i < descriptions.length; i++) {
            int[] description = descriptions[i];
            int p = description[0];
            int c = description[1];
            boolean isLeft = description[2] == 1;

            children.add(c);
            parentMap.computeIfAbsent(p, k -> new int[2])[isLeft ? 0 : 1] = c;
        }

        int rootVal = -1;

        for (Map.Entry<Integer, int[]> entry : parentMap.entrySet()) {
            if (!children.contains(entry.getKey())) {
                rootVal = entry.getKey();
                break;
            }
        }

        TreeNode root = new TreeNode(rootVal);

        dfs(root, parentMap);

        return root;
    }

    public static void dfs(TreeNode root, Map<Integer, int[]> parentMap) {
        int[] ints = parentMap.get(root.val);

        if (ints == null) {
            return;
        }

        if (ints[0] != 0) {
            TreeNode left = new TreeNode(ints[0]);
            dfs(left, parentMap);
            root.left = left;
        }

        if (ints[1] != 0) {
            TreeNode right = new TreeNode(ints[1]);
            dfs(right, parentMap);
            root.right = right;
        }

    }

}
