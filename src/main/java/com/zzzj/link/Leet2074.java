package com.zzzj.link;

/**
 * @author zzzj
 * @create 2022-08-05 10:23
 */
public class Leet2074 {


    public static void main(String[] args) {
        // [1,5,0,2,4,7,3,6]
        // [1,0,5,2,4,7,3,6]
        // [1,0,5,2,4,7,6,3]
        System.out.println(reverseEvenLengthGroups(ListNode.build("[1,5,0,2,4,7,3,6]")).toString(true));
    }

    public static ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null) {
            return null;
        }

        int size = 0;

        ListNode dummy = new ListNode();

        dummy.next = head;

        ListNode prev = dummy;

        ListNode cur = head;

        while (cur != null) {
            size++;
            cur = cur.next;
        }

        cur = head;

        int index = 0;

        int reverseIndex = 0;
        int unReverseIndex = 0;

        int step = 1;

        while (index < size) {
            if (index == unReverseIndex) {
                reverseIndex = unReverseIndex + step;
                step++;

                if (index + step > size && (size - index) % 2 == 0) {
                    ListNode reverse = reverse(cur, size - index);

                    prev.next = reverse;
                    break;
                }

                // skip until reverseIndex
                while (cur != null && index < reverseIndex) {
                    index++;
                    prev = cur;
                    cur = cur.next;
                }
            } else {
                unReverseIndex = reverseIndex + step;

                // reverse until unReverseIndex
                if (index + step > size && (size - index) % 2 != 0) {
                    break;
                }

                // reverse
                ListNode reverse = reverse(cur, Math.min(step, size - index));

                prev.next = reverse;

                step++;

                index = unReverseIndex;

                prev = cur;
                cur = cur.next;
            }
        }


        return dummy.next;
    }

    public static ListNode reverse(ListNode start, int step) {
        ListNode prev = null;
        ListNode cur = start;

        int index = 0;

        while (index < step) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            index++;
        }

        start.next = cur;

        return prev;
    }

}
