package com.zzzj.bit;

import java.util.Arrays;
import java.util.Comparator;

public class Leet1980 {

    public static void main(String[] args) {

    }

    public static String findDifferentBinaryString(String[] nums) {

        Arrays.sort(nums, Comparator.comparingInt(o -> Integer.parseInt(o, 2)));

        int N = nums.length;

        StringBuilder builder = new StringBuilder(N);

        for (int i = 0; i < N; i++) {
            builder.append('0');
        }

        String cur = builder.toString();

        for (int i = 0; i < N; i++) {
            if (!cur.equals(nums[i])) {
                return cur;
            } else {
                cur = incr(cur);
            }
        }

        return cur;
    }

    public static String incr(String str) {
        StringBuilder builder = new StringBuilder();

        String next = Integer.toBinaryString(Integer.parseInt(str, 2) + 1);

        int pad = str.length() - next.length();

        for (int i = 0; i < pad; i++) {
            builder.append('0');
        }

        builder.append(next);

        return builder.toString();
    }

}
