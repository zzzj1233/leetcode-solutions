package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-22 09:57
 */
public class Leet229 {

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{1, 2, 3, 1}));
    }

    private static List<Integer> result;

    // 时间复杂度为 O(n), 空间复杂度为 O(1)
    public static List<Integer> majorityElement(int[] nums) {
        if (nums == null) {
            return Collections.emptyList();
        }

        result = new ArrayList<>();

        // 1,2,3,1 -> 1

        Arrays.sort(nums);

        int len = 1;

        int max = nums.length / 3 + 1;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                len++;
            } else {
                len = 1;
            }
            if (len >= max) {
                result.add(nums[i]);
                int temp = nums[i];
                while (i + 1 < nums.length && nums[i + 1] == temp) {
                    i++;
                }
            }
        }


        return result;
    }

}
