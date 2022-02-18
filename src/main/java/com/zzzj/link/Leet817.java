package com.zzzj.link;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-12-01 15:08
 */
public class Leet817 {

    public static void main(String[] args) {
        System.out.println(numComponents(ListNode.build(0, 1, 2, 3), new int[]{0, 1, 3}));
    }

    public static int numComponents(ListNode head, int[] nums) {
        if (head == null) {
            return 0;
        }

        Map<Integer, Integer> record = new HashMap<>();

        ListNode cur = head;

        while (cur != null) {
            if (cur.next != null) {
                record.put(cur.val, cur.next.val);
            }
            cur = cur.next;
        }

        int count = 0;

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            Integer mapping = record.get(num);
            if (mapping == null) {
                continue;
            }
            while (mapping != null && set.contains(mapping)) {
                record.remove(mapping);
                mapping = record.get(mapping);
            }
            count++;
        }

        return count;
    }

}
