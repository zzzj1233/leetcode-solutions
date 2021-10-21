package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static List<String> buildList(String src) {
        if (src == null || src.isEmpty()) {
            return Collections.emptyList();
        }

        src = src.substring(1, src.length() - 1);

        String[] strList = src.split(",");

        return Arrays.asList(strList)
                .stream()
                .map(s -> s.substring(1, s.length() - 1))
                .collect(Collectors.toList());
    }

    public static TreeNode buildTree(String src) {
        src = src.substring(1, src.length() - 1);
        String[] strList = src.split(",");

        TreeNode root;
        TreeNode result = null;
        Queue<TreeNode> queue = new LinkedList<>();
        for (int i = 0; i < strList.length; i++) {
            if (i == 0) {
                root = new TreeNode(Integer.parseInt(strList[i]));
                result = root;
                queue.add(root);
            }
            if (!queue.isEmpty()) {
                root = queue.poll();
            } else {
                break;
            }
            if (i + 1 < strList.length && !strList[i + 1].equals("null")) {
                root.left = new TreeNode(Integer.parseInt(strList[i + 1]));
                queue.add(root.left);
            }
            if (i + 2 < strList.length && !strList[i + 2].equals("null")) {
                root.right = new TreeNode(Integer.parseInt(strList[i + 2]));
                queue.add(root.right);
            }
            i = i + 1;
        }
        return result;
    }

}
