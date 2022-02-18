package com.zzzj.leet;

import com.zzzj.leet.Leet206.ListNode;

import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-09-26 11:58
 */
public class Leet143 {

    public static void main(String[] args) {
        final ListNode build = ListNode.build(1, 2, 3, 4);

        reorderList(build);

        System.out.println(build.toString(true));
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        Stack<ListNode> stack = new Stack<>();

        ListNode cur = head.next;

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        ListNode p = head;

        while (true) {
            ListNode pop = stack.pop();
            pop.next = null;
            if (pop == p) {
                break;
            }
            ListNode next = p.next;
            p.next = pop;
            if (next == pop) {
                break;
            }
            pop.next = next;
            p = next;
        }

    }


}
