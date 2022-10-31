package com.zzzj.link;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-12-05 17:15
 */
public class Leet2058 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(ListNode.build(2, 2, 1, 3))));
    }

    public static int[] nodesBetweenCriticalPoints(ListNode head) {

        ListNode cur = head;

        ListNode prev = null;

        int index = 0;

        int leftMax = -1;
        int rightMax = -1;

        int leftMin = -1;
        int rightMin = -1;

        int minDistance = Integer.MAX_VALUE;

        while (cur != null) {
            ListNode next = cur.next;

            if (prev != null && next != null) {
                if (cur.val > prev.val && cur.val > next.val) {
                    if (leftMax == -1) {
                        leftMax = index;
                        rightMax = index;
                    } else {
                        minDistance = Math.min(minDistance, index - rightMax);
                        rightMax = index;
                    }
                    if (rightMin != -1) {
                        minDistance = Math.min(minDistance, rightMax - rightMin);
                    }
                } else if (cur.val < prev.val && cur.val < next.val) {
                    if (leftMin == -1) {
                        leftMin = index;
                        rightMin = index;
                    } else {
                        minDistance = Math.min(minDistance, index - rightMin);
                        rightMin = index;
                    }
                    if (rightMax != -1) {
                        minDistance = Math.min(minDistance, rightMin - rightMax);
                    }
                }
            }

            prev = cur;
            cur = next;
            index++;
        }


        int maxDistance = Math.max(Math.max(sub(rightMax, leftMax), sub(rightMin, leftMin)), Math.max(sub(rightMax, leftMin), sub(rightMin, leftMax)));

        if (minDistance == Integer.MAX_VALUE || maxDistance == Integer.MIN_VALUE) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }

    public static int sub(int x, int y) {
        if (x < 0 || y < 0) {
            return Integer.MIN_VALUE;
        }
        return x - y;
    }

}
