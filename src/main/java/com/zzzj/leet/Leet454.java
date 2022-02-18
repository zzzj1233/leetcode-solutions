package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-09-22 18:17
 */
public class Leet454 {

    public static void main(String[] args) {
        int[] nums1 = {-1, -1};
        int[] nums2 = {-1, 1};
        int[] nums3 = {-1, 1};
        int[] nums4 = {1, -1};

        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length * nums2.length);

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                map.merge(nums1[i] + nums2[j], 1, Integer::sum);
            }
        }
        int sum = 0;
        for (int i : nums3) {
            for (int j : nums4) {
                Integer count = map.get(-(i + j));
                if (count != null) {
                    sum += count;
                }
            }
        }

        return sum;
    }

}
