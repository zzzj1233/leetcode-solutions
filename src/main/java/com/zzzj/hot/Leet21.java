package com.zzzj.hot;

import com.zzzj.link.ListNode;

/**
 * @author zzzj
 * @create 2022-01-25 15:50
 */
public class Leet21 {

    public static void main(String[] args) {
        System.out.println(mergeTwoLists(ListNode.build(1, 2, 4), ListNode.build(1, 3, 4)).toString(true));
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode ans = new ListNode();

        ListNode temp = ans;

        while (list1 != null || list2 != null) {
            if (list1 == null) {
                temp.next = list2;
                break;
            }
            if (list2 == null) {
                temp.next = list1;
                break;
            }
            ListNode l1Next = list1.next;
            ListNode l2Next = list2.next;

            if (list1.val > list2.val) {
                temp.next = list2;
                list2 = l2Next;
            } else {
                temp.next = list1;
                list1 = l1Next;
            }
            temp = temp.next;
        }

        return ans.next;
    }

}
