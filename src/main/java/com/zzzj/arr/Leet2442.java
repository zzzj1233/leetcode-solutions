package com.zzzj.arr;

import java.util.HashSet;
import java.util.Set;

public class Leet2442 {

    public static void main(String[] args) {

        System.out.println(countDistinctIntegers(new int[]{1, 13, 10, 12, 31}));

        System.out.println(countDistinctIntegers(new int[]{2, 2, 2}));

    }

    public static int countDistinctIntegers(int[] nums) {
        Set<String> set = new HashSet<>(nums.length);

        for (int num : nums) {
            set.add(String.valueOf(num));
        }

        // int ans = set.size();

        for (int num : nums) {
            set.add(reverse(num));
        }

        return set.size();
    }

    public static String reverse(int num) {

        int cur = 0;

        while (num > 0) {
            cur = cur * 10 + (num % 10);
            num /= 10;
        }

        return String.valueOf(cur);
    }

}
