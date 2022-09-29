package com.zzzj.greedy;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2022-09-28 12:14
 */
public class Leet1675 {


    public static void main(String[] args) {
        System.out.println(minimumDeviation(new int[]{1, 2, 3, 4}));
        System.out.println(minimumDeviation(new int[]{4, 1, 5, 20, 3}));
        System.out.println(minimumDeviation(new int[]{2, 10, 8}));
    }

    public static int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int num : nums) {
            if (num % 2 != 0) {
                set.add(num << 1);
            } else {
                set.add(num);
            }
        }

        int ans = Integer.MAX_VALUE;

        while (set.last() % 2 == 0) {
            Integer last = set.last();
            ans = Math.min(ans, last - set.first());
            set.remove(last);
            set.add(last / 2);
        }

        ans = Math.min(ans, set.last() - set.first());

        return ans;
    }

}
