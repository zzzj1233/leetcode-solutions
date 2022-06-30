package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-06-29 18:15
 */
public class Leet2150 {

    public static List<Integer> findLonely(int[] nums) {

        Arrays.sort(nums);

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if ((i - 1 < 0 || nums[i] != nums[i - 1]) && (i + 1 == nums.length || nums[i + 1] != nums[i])) {
                if ((i - 1 < 0 || nums[i - 1] < nums[i] - 1) && (i + 1 == nums.length || nums[i + 1] > nums[i] + 1)) {
                    ans.add(nums[i]);
                }
            }
        }

        return ans;
    }

}
