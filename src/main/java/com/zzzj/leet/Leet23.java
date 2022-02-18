package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-09-29 17:05
 */
public class Leet23 {

    public static void main(String[] args) {
        System.out.println(mergeKLists(new ListNode[]{ListNode.build(1, 4, 5), ListNode.build(1, 3, 4), ListNode.build(2, 6)}).toString(true));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.add(list);
            }
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        while (!priorityQueue.isEmpty()) {
            ListNode listNode = priorityQueue.poll();
            if (listNode.next != null) {
                priorityQueue.offer(listNode.next);
            }
            cur.next = new ListNode(listNode.val);
            cur = cur.next;
        }

        return dummy.next;
    }

}
