package com.zzzj.contest.week357;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet6932 {

    public static void main(String[] args) {

        System.out.println(findMaximumElegance(LeetUtils.convertInts("[[3,2],[5,1],[10,1]]"), 2));

        System.out.println(findMaximumElegance(LeetUtils.convertInts("[[3,1],[3,1],[2,2],[5,3]]"), 3));

        System.out.println(findMaximumElegance(LeetUtils.convertInts("[[1,1],[2,1],[3,1]]"), 3));

    }

    public static long findMaximumElegance(int[][] items, int k) {

        Arrays.sort(items, (o1, o2) -> o2[0] - o1[0]);

        int N = items.length;

        Set<Integer> visited = new HashSet<>();

        LinkedList<Integer> duplicated = new LinkedList<>();

        int max = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int profit = items[i][0];

            int category = items[i][1];

            if (i < k) {

                if (!visited.add(category)) {
                    duplicated.add(profit);
                }

                max += profit;
            } else {

                if (visited.contains(category) || duplicated.isEmpty()) continue;

                Integer last = duplicated.removeLast();

                int origin = visited.size() * visited.size();

                int next = (visited.size() + 1) * (visited.size() + 1);

                int diff = next - origin;

                if (last < diff + profit) {
                    max -= last;
                    max += profit;
                    visited.add(category);
                }
            }

            ans = Math.max(ans, max + visited.size() * visited.size());
        }

        return ans;
    }

}
