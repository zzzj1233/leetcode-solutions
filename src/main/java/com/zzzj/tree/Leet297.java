package com.zzzj.tree;

import com.zzzj.leet.TreeNode;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-11-09 15:45
 */
public class Leet297 {

    public static void main(String[] args) {
        String str = serialize(TreeNode.buildTree("[1,2,3,1,3,2,4]"));
        System.out.println(str);
        System.out.println(deserialize(str).serialize());
    }

    public static String serialize(TreeNode root) {
        // 中序序列化
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

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

    // 中序反序列化
    public static TreeNode deserialize(String data) {
        // 每一层要取2的N次方
        String[] split = data.split(",");

        if (split.length == 0 || "null".equals(split[0])) {
            return null;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(Integer.parseInt(split[0]));

        queue.add(root);

        int index = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < Math.pow(2, size); i += 2) {
                if (queue.isEmpty()){
                    return root;
                }

                TreeNode first = queue.removeFirst();
                String leftStr = split[index];
                index++;
                if (!"null".equals(leftStr)) {
                    first.left = new TreeNode(Integer.parseInt(leftStr));
                    queue.add(first.left);
                }

                String rightStr = split[index];
                index++;
                if (!"null".equals(rightStr)) {
                    first.right = new TreeNode(Integer.parseInt(rightStr));
                    queue.add(first.right);
                }
            }

        }
        return root;
    }

}
