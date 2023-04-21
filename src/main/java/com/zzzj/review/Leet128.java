package com.zzzj.review;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-03-16 20:31
 */
public class Leet128 {

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int num : nums) {
            treeSet.add(num);
        }

        Integer num = treeSet.first();

        int max = 1;
        int ans = max;

        Integer bigger = treeSet.higher(num);

        while (bigger != null) {
            if (bigger == num + 1) {
                max++;
            } else {
                max = 1;
            }
            ans = Math.max(ans, max);
            num = bigger;
            bigger = treeSet.higher(num);
        }

        return ans;
    }

}
