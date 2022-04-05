package com.zzzj.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-04-17 19:21
 */
public class Leet163 {

    public static void main(String[] args) {
        System.out.println(findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 50));
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > lower) {
                int low = nums[i] - lower;
                ans.add(low == 1 ? String.valueOf(lower) : String.format("%d->%d", lower, nums[i] - 1));
            }
            lower = nums[i] + 1;
        }


        if (nums.length == 0 && lower <= upper) {
            int low = upper - lower;
            ans.add(low == 0 ? String.valueOf(lower) : String.format("%d->%d", lower, upper));
        } else if (lower < upper) {
            int low = upper - lower;
            ans.add(low == 1 ? String.valueOf(lower) : String.format("%d->%d", lower, upper));
        }

        return ans;
    }

}
