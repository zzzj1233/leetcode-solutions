package com.zzzj.tree;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-08-08 14:34
 */
public class Leet1305 {

    public static void main(String[] args) {
        System.out.println(getAllElements(TreeNode.buildTree("[3,2]"), TreeNode.buildTree("[1,0]")));
    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        inOrder(root1, list1);
        inOrder(root2, list2);

        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();

        int total = list1.size() + list2.size();

        List<Integer> ans = new ArrayList<>(total);

        while (!list1.isEmpty() || !list2.isEmpty()) {
            if (list1.isEmpty()) {
                ans.addAll(list2);
                break;
            } else if (list2.isEmpty()) {
                ans.addAll(list1);
                break;
            } else {
                int value1 = list1.peekFirst();
                int value2 = list2.peekFirst();

                if (value1 == value2) {
                    ans.add(value1);
                    ans.add(value1);
                    list1.removeFirst();
                    list2.removeFirst();
                } else if (value1 < value2) {
                    while (!list1.isEmpty() && list1.peekFirst() < value2) {
                        ans.add(list1.removeFirst());
                    }
                    ans.add(value2);
                    list2.removeFirst();
                } else {
                    while (!list2.isEmpty() && list2.peekFirst() < value1) {
                        ans.add(list2.removeFirst());
                    }
                    ans.add(value1);
                    list1.removeFirst();
                }
            }
        }

        return ans;
    }

    public static void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

}
