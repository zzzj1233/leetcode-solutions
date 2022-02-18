package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-25 18:51
 */
public class Leet876 {

    public static ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;


        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

}
