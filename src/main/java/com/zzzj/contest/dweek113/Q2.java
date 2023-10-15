package com.zzzj.contest.dweek113;

import java.util.*;

public class Q2 {

    public static void main(String[] args) {

        // 0
        System.out.println(minLengthAfterRemovals(Arrays.asList(1, 3, 4, 9)));

        // 0
        System.out.println(minLengthAfterRemovals(Arrays.asList(2, 3, 6, 9)));

        // 1
        System.out.println(minLengthAfterRemovals(Arrays.asList(1, 1, 2)));

        // 1
        System.out.println(minLengthAfterRemovals(Arrays.asList(1, 2, 2)));

        // 1
        System.out.println(minLengthAfterRemovals(Arrays.asList(1, 3, 4, 4, 4)));

        // 0
        System.out.println(minLengthAfterRemovals(Arrays.asList(1, 1, 2, 3, 4, 4)));

        // 1
        System.out.println(minLengthAfterRemovals(Arrays.asList(1, 3, 3, 3, 4)));

        System.out.println(minLengthAfterRemovals(Arrays.asList(1, 1, 4, 4, 5, 5)));

    }

    public static int minLengthAfterRemovals(List<Integer> list) {

        int N = list.size();

        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = list.get(i);
        }

        TreeMap<Integer, Integer> indexes = new TreeMap<>();

        Map<Integer, Integer> cnt = new HashMap<>();

        for (int i = 0; i < N; i++) {
            indexes.putIfAbsent(nums[i], i);
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
        }

        if (indexes.size() == 1)
            return N;

        int ans = N;

        List<Integer> idxs = new ArrayList<>(indexes.values());

        for (int i = 0; i < idxs.size(); i++) {

            Integer index = idxs.get(i);

            // 小于我的有多少个
            int less = index;

            // 我有多少个
            int c = cnt.get(nums[index]);

            // 大于我的有多少个
            int bigger = (i + 1 == idxs.size() ? 0 : (N - idxs.get(i + 1)));

            if (bigger == c && c == less)
                return 0;
            ans = Math.min(ans, Math.abs(bigger - Math.abs(c - less)));
        }

        return ans;
    }

}
