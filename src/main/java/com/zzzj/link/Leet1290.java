package com.zzzj.link;

/**
 * @author zzzj
 * @create 2021-11-25 12:06
 */
public class Leet1290 {

    public static int getDecimalValue(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder(30);

        while (head != null) {
            stringBuilder.append(head.val);
            head = head.next;
        }

        return Integer.parseInt(stringBuilder.toString(), 2);
    }

}
