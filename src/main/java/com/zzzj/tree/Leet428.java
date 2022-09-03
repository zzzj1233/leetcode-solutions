package com.zzzj.tree;

import java.util.ArrayList;

/**
 * @author zzzj
 * @create 2022-08-31 10:36
 */
public class Leet428 {

    public static void main(String[] args) {
        Node[] nodes = Node.nodes(14);

        nodes[1].children.add(nodes[2]);
        nodes[1].children.add(nodes[3]);
        nodes[1].children.add(nodes[4]);
        nodes[1].children.add(nodes[5]);

        nodes[3].children.add(nodes[6]);
        nodes[3].children.add(nodes[7]);

        nodes[4].children.add(nodes[8]);

        nodes[5].children.add(nodes[9]);
        nodes[5].children.add(nodes[10]);

        System.out.println(serialize(deserialize(serialize(nodes[1]))));

        System.out.println(serialize(deserialize(serialize(nodes[1]))).equals(serialize(nodes[1])));
    }

    // [1[3[5,6],2,4]]
    public static String serialize(Node root) {
        if (root == null) {
            return "";
        }
        return "[" + dfs(root) + "]";
    }

    public static String dfs(Node root) {
        if (root == null) {
            return "";
        }

        if (root.children == null || root.children.isEmpty()) {
            return String.valueOf(root.val);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        builder.append("[");

        int size = root.children.size();

        for (int i = 0; i < size; i++) {
            Node node = root.children.get(i);
            builder.append(dfs(node));
            if (i != size - 1) {
                builder.append(",");
            }
        }

        builder.append("]");
        return builder.toString();
    }

    // [1[3[5,6],2,4]]
    public static Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        Node dummy = new Node(-1, new ArrayList<>(1));
        dfs(data, 0, dummy);
        return dummy.children.get(0);
    }

    public static int dfs(String data, int i, Node parent) {
        Node node = parent;
        while (i < data.length() && data.charAt(i) != ']') {
            char c = data.charAt(i);
            if (c == '[') {
                i = dfs(data, i + 1, node);
            } else if (c == ',') {
                i++;
            } else {
                int num = 0;
                while (Character.isDigit(data.charAt(i))) {
                    num = num * 10 + Character.digit(data.charAt(i), 10);
                    i++;
                }
                node = new Node(num, new ArrayList<>(0));
                parent.children.add(node);
            }
        }

        return i + 1;
    }

}
