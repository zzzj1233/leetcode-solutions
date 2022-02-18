package com.zzzj.link;


import java.util.Arrays;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-12-01 11:38
 */
public class Leet1019 {

    public static void main(String[] args) {
        // 1 7 5 1 9 2 5 1
        // 1 5 2 9 1 5 7 1
        // 7,9,9,9,0,5,0,0
        int[] answer = nextLargerNodes(ListNode.build(1, 7, 5, 1, 9, 2, 5, 1));
        System.out.println(Arrays.toString(answer));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        ListNode next = null;
        ListNode prev = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        Stack<Integer> stack = new Stack<>();

        head = reverseList(head);

        stack.add(head.val);

        ListNode cur = head.next;

        int len = 1;

        while (cur != null) {
            if (cur.val > stack.peek()) {
                stack.add(cur.val);
            } else {
                stack.add(stack.peek());
            }
            cur = cur.next;
            len++;
        }

        head = reverseList(head);
        cur = head;

        int[] answer = new int[len];

        int i = 0;

        while (cur != null) {
            Integer pop = stack.pop();
            if (cur.val >= pop) {
                answer[i] = 0;
            } else {
                // 找到第一个比当前元素大的元素
                ListNode tmp = cur.next;
                while (tmp.val <= cur.val) {
                    tmp = tmp.next;
                }
                answer[i] = tmp.val;
            }
            i++;
            cur = cur.next;
        }

        return answer;
    }

}
