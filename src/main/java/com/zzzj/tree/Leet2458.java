package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-09-04 12:06
 */
public class Leet2458 {

    public static int[] treeQueries(TreeNode root, int[] queries) {

        Map<Integer, TreeNode> parentMap = new HashMap<>();

        Map<Integer, Integer> heightMap = new HashMap<>();

        buildParentMap(root, null, parentMap);

        height(root, heightMap);

        int N = queries.length;

        int[] ans = new int[N];

        for (int i = 0; i < N; i++)
            ans[i] = getAns(heightMap.get(root.val), parentMap, heightMap, queries[i]);

        return ans;
    }

    public static int getAns(int highest, Map<Integer, TreeNode> parentMap, Map<Integer, Integer> heightMap, int rmNode) {

        TreeNode p;

        int curValue = rmNode;

        int curHeight = heightMap.get(curValue);

        return -1;
    }

    private static void buildParentMap(TreeNode root, TreeNode parent, Map<Integer, TreeNode> parentMap) {
        if (root == null) return;

        parentMap.put(root.val, parent);

        buildParentMap(root.left, root, parentMap);

        buildParentMap(root.right, root, parentMap);
    }

    private static int height(TreeNode root, Map<Integer, Integer> heightMap) {

        if (root == null) return 0;

        int leftHeight = height(root.left, heightMap);

        int rightHeight = height(root.left, heightMap);

        int h = Math.max(leftHeight, rightHeight) + 1;

        heightMap.put(root.val, h);

        return h;
    }

}
