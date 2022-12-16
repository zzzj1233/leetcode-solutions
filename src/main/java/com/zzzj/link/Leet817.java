package com.zzzj.link;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-12-16 12:04
 */
public class Leet817 {

    public static int numComponents(ListNode head, int[] nums) {
        int ans = 1;

        Set<Integer> set = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());

        ListNode cur = head;

        boolean in = false;

        while (cur != null && !set.isEmpty()) {
            int val = cur.val;

            if (in && !set.remove(val)) {
                in = false;
                ans++;
            }

            in |= set.remove(val);

            ListNode next = cur.next;

            cur = next;
        }

        return ans;
    }

}
