package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
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

    public String serialize() {
        // 中序序列化
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(this);

        LinkedList<String> path = new LinkedList<>();

        while (!queue.isEmpty()) {
            TreeNode first = queue.removeFirst();
            if (first != null) {
                path.add(String.valueOf(first.val));
                queue.add(first.left);
                queue.add(first.right);
            } else {
                path.add("null");
            }
        }

        return String.join(",", path);
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

    public List<Integer> preOrder() {
        List<Integer> list = new LinkedList<>();
        preOrder(this, list);
        return list;
    }

    public List<Integer> inOrder() {
        List<Integer> list = new LinkedList<>();
        inOrder(this, list);
        return list;
    }

    private static void inOrder(TreeNode node, List<Integer> list) {
        if (node.left != null) {
            inOrder(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            inOrder(node.right, list);
        }
    }

    private static void preOrder(TreeNode node, List<Integer> list) {
        list.add(node.val);
        if (node.left != null) {
            preOrder(node.left, list);
        }
        if (node.right != null) {
            preOrder(node.right, list);
        }
    }

}
