package com.zzzj.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-04-28 14:35
 */
public class Leet2476 {

    public static void main(String[] args) {

        System.out.println(closestNodes(
                TreeNode.buildTree("[6,2,13,1,4,9,15,null,null,null,null,null,null,14]"),
                Arrays.asList(2, 5, 16)
        ));

        System.out.println(closestNodes(
                TreeNode.buildTree("[4,null,9]"),
                Arrays.asList(3)
        ));

    }

    public static List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        int N = queries.size();

        List<List<Integer>> ans = new ArrayList<>(N);

        TreeSet<Integer> set = new TreeSet<>();

        dfs(root, set);

        for (int i = 0; i < N; i++) {
            Integer ceiling = set.ceiling(queries.get(i));
            Integer floor = set.floor(queries.get(i));

            ans.add(
                    Arrays.asList(
                            floor == null ? -1 : floor,
                            ceiling == null ? -1 : ceiling
                    )
            );
        }

        return ans;
    }

    public static void dfs(TreeNode root, TreeSet<Integer> set) {
        if (root == null) {
            return;
        }

        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }

}
