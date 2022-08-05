package com.zzzj.link;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-11-30 14:09
 */
public class Leet725 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(splitListToParts(ListNode.build(1, 2, 3, 4, 5, 6, 7), 3)));
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];
        ListNode[] dummy = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ListNode dummyNode = new ListNode();
            ans[i] = dummyNode;
            dummy[i] = dummyNode;
        }


        int ansIndex = 0;

        ListNode node = head;

        int size = 0;

        while (node != null) {
            size++;
            node = node.next;
        }

        int perBucket = size / k;
        int extraEnd = size % k;

        node = head;

        for (int i = 0; i < k && node != null; i++) {
            int per = perBucket;
            if (i < extraEnd) {
                // 多一个
                per++;
            }

            ListNode prev = null;
            for (int j = 0; j < per && node != null; j++) {
                ans[i].next = node;
                ans[i] = ans[i].next;
                prev = node;
                node = node.next;
            }

            if (prev != null) {
                prev.next = null;
            }
        }

        for (int i = 0; i < k; i++) {
            ans[i] = dummy[i].next;
        }

        return ans;
    }


}
