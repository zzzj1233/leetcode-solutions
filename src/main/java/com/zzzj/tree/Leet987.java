package com.zzzj.tree;

import org.omg.CORBA.OMGVMCID;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-08-31 18:32
 */
public class Leet987 {

    public static void main(String[] args) {
        // System.out.println(verticalTraversal(TreeNode.buildTree("[3,9,20,null,null,15,7]")));

//        System.out.println(verticalTraversal(TreeNode.buildTree("[1,2,3,4,5,6,7]")));

        System.out.println(verticalTraversal(TreeNode.buildTree("[3,1,4,0,2,2]")));
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {

        TreeMap<Integer, TreeMap<Integer, List<Integer>>> rec = new TreeMap<>();

        dfs(root, rec, 0, 0);

        List<List<Integer>> ans = new ArrayList<>(rec.size());

        for (Map.Entry<Integer, TreeMap<Integer, List<Integer>>> entry : rec.entrySet()) {

            TreeMap<Integer, List<Integer>> map = entry.getValue();

            List<Integer> inner = new ArrayList<>();

            for (Map.Entry<Integer, List<Integer>> listEntry : map.entrySet()) {
                inner.addAll(
                        listEntry.getValue().stream().sorted().collect(Collectors.toList())
                );
            }

            ans.add(inner);
        }

        return ans;
    }

    private static final Function<Integer, TreeMap<Integer, List<Integer>>> FNC = integer -> new TreeMap<>();

    private static final Function<Integer, List<Integer>> FNC2 = integer -> new ArrayList<>();

    public static void dfs(TreeNode node, TreeMap<Integer, TreeMap<Integer, List<Integer>>> rec, int r, int c) {
        if (node == null)
            return;

        rec.computeIfAbsent(c, FNC).computeIfAbsent(r, FNC2).add(node.val);

        dfs(node.left, rec, r + 1, c - 1);
        dfs(node.right, rec, r + 1, c + 1);
    }

}
