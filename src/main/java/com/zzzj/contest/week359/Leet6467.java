package com.zzzj.contest.week359;

import java.util.*;

public class Leet6467 {

    public static void main(String[] args) {

        System.out.println(longestEqualSubarray(Arrays.asList(1, 3, 2, 3, 1, 3), 3));

        System.out.println(longestEqualSubarray(Arrays.asList(1, 1, 2, 2, 1, 1), 2));

        System.out.println(longestEqualSubarray(Arrays.asList(5, 4, 4, 2, 4, 8, 9, 1, 3, 4, 7, 5, 7, 4), 4));
//
        System.out.println(longestEqualSubarray(Arrays.asList(3, 1, 5, 3, 1, 1), 0));

    }

    public static int longestEqualSubarray(List<Integer> nums, int k) {

        Map<Integer, List<Integer>> indexes = new HashMap<>();

        for (int i = 0; i < nums.size(); i++) {
            Integer num = nums.get(i);
            indexes.computeIfAbsent(num, integer -> new ArrayList<>())
                    .add(i);
        }

        int ans = 0;

        for (List<Integer> value : indexes.values())
            ans = Math.max(ans, longest(value, k));
        return ans;
    }

    public static int longest(List<Integer> indexes, int k) {
        int left = 0;
        int right = 1;
        int max = 1;

        int N = indexes.size();

        while (right < N) {
            Integer R = indexes.get(right);

            Integer L = indexes.get(left);

            int diff = R - L + 1 - (right - left + 1);

            while (diff > k) {
                left++;
                if (left >= indexes.size()) return max;
                L = indexes.get(left);
                diff = R - L + 1 - (right - left + 1);
            }

            max = Math.max(max, right - left + 1);

            right++;
        }

        return max;
    }

}
