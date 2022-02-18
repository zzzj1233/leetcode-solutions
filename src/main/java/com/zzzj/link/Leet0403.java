package com.zzzj.link;

import com.zzzj.leet.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-11-25 12:14
 */
public class Leet0403 {

    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[0];
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(tree);

        List<ListNode> list = new LinkedList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            ListNode dummy = new ListNode();
            ListNode save = dummy;
            for (int i = 0; i < size; i++) {
                TreeNode first = queue.removeFirst();
                dummy.next = new ListNode(first.val);
                dummy = dummy.next;
                if (first.left != null) {
                    queue.add(first.left);
                }
                if (first.right != null) {
                    queue.add(first.right);
                }
            }

            list.add(save.next);
        }

        ListNode[] answer = new ListNode[list.size()];

        Iterator<ListNode> iterator = list.iterator();

        int i = 0;

        while (iterator.hasNext()) {
            answer[i++] = iterator.next();
        }

        return answer;
    }

}
