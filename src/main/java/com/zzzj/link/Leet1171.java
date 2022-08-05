package com.zzzj.link;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2021-12-05 17:21
 */
public class Leet1171 {

    public static void main(String[] args) {

        System.out.println(removeZeroSumSublists(ListNode.build("[0,0]")).toString(true));

    }

    // [1,3,2,-3,-2,5,5,-5,1]
    public static ListNode removeZeroSumSublists(ListNode head) {

        Map<Integer, ListNode> preSum = new HashMap<>();

        ListNode dummy = new ListNode();
        dummy.next = head;

        preSum.put(0, dummy);

        int sum = 0;

        ListNode node = head;

        while (node != null) {
            sum += node.val;
            if (preSum.containsKey(sum)) {
                ListNode preNode = preSum.get(sum);
                if (preNode != null) {
                    while (node != null && node.next.val == 0) {
                        node = node.next;
                    }
                    preNode.next = node.next;
                }
            }
            preSum.put(sum, node);
            node = node.next;
        }

        return dummy.next;
    }

}
