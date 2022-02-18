package com.zzzj.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-10-26 10:04
 */
public class Leet496 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4})));
    }

    // 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        Map<Integer, Integer> map = new HashMap<>(nums2.length);

        for (int i = 0; i < nums2.length - 1; i++) {
            map.put(nums2[i], nums2[i + 1]);
        }

        for (int i = 0; i < nums1.length; i++) {
            Integer next = map.get(nums1[i]);
            if (next == null) {
                result[i] = -1;
            } else if (next < nums1[i]) {
                while (next != null && next < nums1[i]) {
                    next = map.get(next);
                }
                if (next == null) {
                    result[i] = -1;
                } else {
                    result[i] = next;
                }
            } else {
                result[i] = next;
            }
        }

        return result;
    }


}
