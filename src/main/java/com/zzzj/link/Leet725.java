package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-30 14:09
 */
public class Leet725 {

    public static void main(String[] args) {
        splitListToParts(ListNode.build(1, 2, 3, 4, 5, 6, 7), 3);
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] answer = new ListNode[k];

        ListNode cur = head;

        int len = 0;

        // 1. 求长度
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        // 2. 余数
        int remainder = len > k ? len % k : 0;

        // 3. 初始化answer
        for (int i = 0; i < k; i++) {
            answer[i] = new ListNode();
        }

        cur = head;

        int index = 0;

        int basicEnd = len > k ? k : 1;

        while (cur != null) {
            int end = index % k < remainder ? k + 1 : basicEnd;
            for (int i = 0; i < end && cur != null; i++, cur = cur.next) {
                appendEnd(answer[index % k], new ListNode(cur.val));
            }
            index++;
        }

        for (int i = 0; i < k; i++) {
            answer[i] = answer[i].next;
        }


        return answer;
    }

    private static void appendEnd(ListNode node, ListNode newNode) {
        ListNode cur = node;

        while (cur.next != null) {
            cur = cur.next;
        }

        cur.next = newNode;
    }

}
