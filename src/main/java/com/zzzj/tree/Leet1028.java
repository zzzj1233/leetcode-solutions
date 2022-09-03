package com.zzzj.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-31 11:41
 */
public class Leet1028 {

    public static void main(String[] args) {
        System.out.println(recoverFromPreorder("1-2--3--4-5--6--7").serialize());
    }

    public static TreeNode recoverFromPreorder(String traversal) {
        Map<Integer, TreeNode> map = new HashMap<>();

        int[] result = getNum(traversal, 0);

        TreeNode root = new TreeNode(result[0]);

        map.put(1, root);

        dfs(traversal, result[1], map);

        return root;
    }

    public static void dfs(String traversal, int index, Map<Integer, TreeNode> map) {
        if (index >= traversal.length()) {
            return;
        }
        int nextIndex = skipDash(traversal, index);

        int dashLen = nextIndex - index;

        int[] ints = getNum(traversal, nextIndex);

        int num = ints[0];

        nextIndex = ints[1];

        TreeNode node = new TreeNode(num);

        TreeNode parent = map.get(dashLen);

        if (parent.left == null) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        map.put(dashLen + 1, node);

        dfs(traversal, nextIndex, map);
    }

    public static int[] getNum(String traversal, int index) {
        int num = 0;

        while (index < traversal.length() && Character.isDigit(traversal.charAt(index))) {
            num = num * 10 + Character.digit(traversal.charAt(index), 10);
            index++;
        }

        return new int[]{num, index};
    }

    public static int skipDash(String traversal, int index) {
        while (traversal.charAt(index) == '-') {
            index++;
        }
        return index;
    }

}
