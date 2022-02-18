package com.zzzj.link;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-11-29 12:12
 */
public class Leet2348 {

    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        Map<Integer, Boolean> record = new HashMap<>();

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode cur = head;

        ListNode prev = dummy;

        while (cur != null) {
            if (record.get(cur.val) != null) {
                prev.next = cur.next;
            } else {
                record.put(cur.val, Boolean.TRUE);
                prev = cur;
            }
            cur = cur.next;
        }


        return dummy.next;
    }


}
