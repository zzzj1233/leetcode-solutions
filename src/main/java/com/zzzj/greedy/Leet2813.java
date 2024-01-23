package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-09-22 12:40
 */
public class Leet2813 {

    public static void main(String[] args) {

        System.out.println(findMaximumElegance(LeetUtils.convertInts("[[10,1],[10,1],[10,1],[10,1],[10,1],[10,1],[10,1],[10,1],[10,1],[10,1],[3,2],[3,3],[3,4],[3,5],[3,6],[3,7],[3,8],[3,9],[3,10],[3,11]]"), 10));

    }

    private static final int INDEX_PROFIT = 0;

    private static final int INDEX_CATEGORY = 1;

    public static long findMaximumElegance(int[][] items, int k) {

        Arrays.sort(items, (o1, o2) -> o2[INDEX_PROFIT] - o1[INDEX_PROFIT]);

        Map<Integer, Integer> holding = new HashMap<>();

        int N = items.length;

        long profit = 0;

        long max = 0;

        long ans = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> items[o][INDEX_PROFIT]));

        int hold = 0;

        for (int i = 0; i < N; i++) {

            int p = items[i][INDEX_PROFIT];
            int c = items[i][INDEX_CATEGORY];

            while (!queue.isEmpty() && holding.get(items[queue.peek()][INDEX_CATEGORY]) == 1) {
                queue.remove();
            }

            if (hold < k) {

                Integer old = holding.getOrDefault(c, 0);

                if (old == 0) {
                    holding.put(c, 1);
                } else {
                    holding.put(c, old + 1);
                    queue.add(i);
                }

                profit += p;

                max = profit + (long) holding.size() * holding.size();

                hold++;

            } else if (!holding.containsKey(c) && !queue.isEmpty()) {

                int peek = queue.peek();

                int distinctiveCnt = holding.size() + 1;

                queue.remove();

                holding.put(c, 1);

                profit = profit - items[peek][INDEX_PROFIT] + p;

                max = profit + (long) holding.size() * holding.size();

                holding.put(items[peek][INDEX_CATEGORY], holding.get(items[peek][INDEX_CATEGORY]) - 1);

            }

            ans = Math.max(ans, max);
        }

        return ans;
    }

}
