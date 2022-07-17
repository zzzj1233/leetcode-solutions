package com.zzzj.daily;

import com.zzzj.tree.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zzzj
 * @create 2022-06-19 21:12
 */
public class Leet508 {

    private static class Node {
        int value;
        int count;
    }

    private static HashMap<Integer, Node> map;

    private static int max;

    public static int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        map = new HashMap<>();
        max = 0;

        dfs(root);

        List<Integer> list = map.values()
                .stream()
                .filter(node -> node.count == max)
                .map(node -> node.value)
                .collect(Collectors.toList());

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    public static int wrap(int value) {
        if (!map.containsKey(value)) {
            Node node = new Node();
            node.value = value;
            node.count = 0;
            map.put(value, node);
        }

        Node node = map.get(value);
        node.count++;
        max = Math.max(max, node.count);
        return value;
    }

    public static int dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return wrap(root.val);
        }
        if (root.left == null) {
            return wrap(root.val + dfs(root.right));
        }
        if (root.right == null) {
            return wrap(root.val + dfs(root.left));
        }
        return wrap(root.val + dfs(root.right) + dfs(root.left));
    }

}
