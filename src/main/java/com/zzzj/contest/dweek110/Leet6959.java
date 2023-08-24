package com.zzzj.contest.dweek110;

import java.util.*;

public class Leet6959 {


    public static void main(String[] args) {

        System.out.println(minimumSeconds(Arrays.asList(7, 6, 7, 16, 19, 16, 16)));
    }


    public static int minimumSeconds(List<Integer> nums) {

        int N = nums.size();

        int[] leftDiff = new int[N];

        int[] rightDiff = new int[N];

        Map<Integer, Integer> rec = new HashMap<>(N);

        Map<Integer, TreeSet<Integer>> indexes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            Integer num = nums.get(i);
            int cnt = rec.getOrDefault(num, 0) + 1;
            rec.put(num, cnt);
            leftDiff[i] = (i + 1) - cnt;
            indexes.computeIfAbsent(num, integer -> new TreeSet<>()).add(i);
        }

        if (rec.size() == 1) return 0;

        rec.clear();

        for (int i = N - 1; i >= 0; i--) {
            Integer num = nums.get(i);
            int cnt = rec.getOrDefault(num, 0) + 1;
            rec.put(num, cnt);
            rightDiff[i] = (N - i) - cnt;
        }


        int ans = Integer.MAX_VALUE;

        Map<Integer, Integer> ansMap = new HashMap<>();

        for (int i = 0; i < N; i++) {

            Integer num = nums.get(i);

            if (i == indexes.get(num).first()) {
                continue;
            }

            Integer prevIndex = indexes.get(num).floor(i - 1);

            int cnt = (int) Math.ceil((rightDiff[prevIndex] - rightDiff[i]) / 2.0);

            Integer old = ansMap.getOrDefault(num, 0);

            if (cnt > old) {
                ansMap.put(num, cnt);
                old = cnt;
            }

            if (i == indexes.get(num).last()) {

                int outerDiff = leftDiff[indexes.get(num).first()] + rightDiff[i];

                int outerCnt = (int) Math.ceil(outerDiff / 2.0);

                if (outerCnt > old) {
                    ansMap.put(num, outerCnt);
                }

                ans = Math.min(ans, ansMap.get(num));
            }

        }

        return ans == Integer.MAX_VALUE ? N / 2 : ans;
    }


}
