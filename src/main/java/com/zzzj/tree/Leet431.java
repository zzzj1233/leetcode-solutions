package com.zzzj.tree;

import java.util.ArrayList;

/**
 * @author zzzj
 * @create 2022-08-31 18:20
 */
public class Leet431 {

    public static void main(String[] args) {
        Node[] nodes = Node.nodes(9);

        nodes[1].children.add(nodes[2]);
        nodes[1].children.add(nodes[3]);
        nodes[1].children.add(nodes[4]);

        nodes[2].children.add(nodes[5]);
        nodes[2].children.add(nodes[6]);

        nodes[3].children.add(nodes[7]);
        nodes[3].children.add(nodes[8]);
        nodes[3].children.add(nodes[9]);

        System.out.println(encode(decode(encode(nodes[1]))).serialize());
    }

    // 除了第一个孩子节点,其余的孩子节点全部放在左节点的右节点上
    public static TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        return dfs(root);
    }

    public static TreeNode dfs(Node root) {
        TreeNode treeNode = new TreeNode(root.val);

        if (root.children == null || root.children.isEmpty()) {
            return treeNode;
        }

        Node firstChild = root.children.get(0);

        treeNode.left = dfs(firstChild);

        if (root.children.size() > 1) {
            TreeNode right = treeNode.left;
            for (int i = 1; i < root.children.size(); i++) {
                Node child = root.children.get(i);
                right.right = dfs(child);
                right = right.right;
            }
        }

        return treeNode;
    }


    public static Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return dfs(root);
    }

    public static Node dfs(TreeNode root) {
        // 左孩子的右孩子是当前节点的children
        Node node = new Node(root.val, new ArrayList<>(0));

        if (root.left != null) {
            node.children.add(dfs(root.left));
            TreeNode lr = root.left.right;

            while (lr != null) {
                node.children.add(dfs(lr));
                lr = lr.right;
            }
        }

        return node;
    }

}
