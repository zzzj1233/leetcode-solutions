package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-08-22 10:59
 */
public class Leet1474 {

    public static void main(String[] args) {
        System.out.println(deleteNodes(ListNode.build("[1,2,3,4,5,6,7,8,9,10,11,12,13]"), 2, 3).toString(true));
    }

    public static ListNode deleteNodes(ListNode head, int m, int n) {
        // 保留M个节点,删除N个节点 直到末尾

        ListNode cur = head;

        int preserve = m;
        int remove = 0;

        ListNode prev = null;

        while (cur != null) {
            if (preserve > 0) {
                prev = cur;
                cur = cur.next;
                if (--preserve == 0) {
                    remove = n;
                }
                continue;
            } else {
                ListNode end = cur;
                while (remove > 0 && end != null) {
                    end = end.next;
                    remove--;
                }
                prev.next = end;
                cur = end;
                preserve = m;
            }
        }

        return head;
    }

}
