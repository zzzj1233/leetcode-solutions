package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-30 11:17
 */
public class LeetOffer78 {

    public static void main(String[] args) {
        mergeKLists(new ListNode[]{ListNode.build(1, 4, 5), ListNode.build(1, 3, 4), ListNode.build(2, 6)});
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode answer = new ListNode();

        ListNode cur = answer;

        while (true) {
            int min = 0;

            boolean allNull = true;

            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    allNull = false;
                    if (lists[min] == null) {
                        min = i;
                    } else if (lists[i].val < lists[min].val) {
                        min = i;
                    }
                }
            }

            if (allNull) {
                break;
            }

            ListNode next = lists[min].next;

            lists[min].next = null;

            cur.next = lists[min];

            cur = cur.next;

            lists[min] = next;

        }

        return answer.next;
    }

}
