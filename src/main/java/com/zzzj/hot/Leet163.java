package com.zzzj.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-04-17 19:21
 */
public class Leet163 {

    public static void main(String[] args) {
        System.out.println(findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {

        long prev = lower;

        int N = nums.length;

        List<String> ans = new ArrayList<>();

        // num >= lower && num <= upper
        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (prev < num) {
                if (prev + 1 == num) {
                    ans.add(String.valueOf(prev));
                } else {
                    ans.add(prev + "->" + (num - 1));
                }
            }
            prev = num + 1;
        }

        if (prev < upper) {
            if (prev + 1 == upper) {
                ans.add(String.valueOf(prev));
            } else {
                ans.add(prev + "->" + (upper));
            }
        }

        return ans;
    }

}
