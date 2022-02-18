package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-26 18:47
 */
public class Leet141 {

    // 判断一个链表是否有环
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
            if (fast == null) {
                break;
            }
            if (fast == slow) {
                return true;
            }
            fast = fast.next;

            slow = slow.next;
        }

        return false;
    }

}
