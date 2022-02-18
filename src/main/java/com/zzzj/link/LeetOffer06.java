package com.zzzj.link;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-11-26 15:18
 */
public class LeetOffer06 {

    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }

        LinkedList<Integer> list = new LinkedList<>();

        ListNode cur = head;

        while (cur != null) {
            list.addFirst(cur.val);
            cur = cur.next;
        }

        int[] answer = new int[list.size()];

        int i = 0;

        for (Integer val : list) {
            answer[i] = val;
            i++;
        }

        return answer;
    }

}
