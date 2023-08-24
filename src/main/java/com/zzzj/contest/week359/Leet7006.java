package com.zzzj.contest.week359;

import com.zzzj.leet.LeetUtils;

import java.util.*;

public class Leet7006 {

    public static void main(String[] args) {

        System.out.println(maximizeTheProfit(5, LeetUtils.convertLists("[[0,0,1],[0,2,2],[1,3,2]]")));

        System.out.println(maximizeTheProfit(5, LeetUtils.convertLists("[[0,0,1],[0,2,10],[1,3,2]]")));

        // expect: 12
        System.out.println(maximizeTheProfit(9, LeetUtils.convertLists("[[2,3,4],[7,7,8],[2,5,3]]")));

        // 10
        // [[3,9,3],[0,7,8],[1,4,4],[0,9,6],[5,5,10],[3,5,4],[4,9,5],[8,9,6]]
        // 20
        System.out.println(maximizeTheProfit(10, LeetUtils.convertLists("[[0, 7, 8], [0, 9, 6], [1, 4, 4], [3, 5, 4], [3, 9, 3], [4, 9, 5], [5, 5, 10], [8, 9, 6]]")));

    }

    public static int maximizeTheProfit(int n, List<List<Integer>> offers) {

        offers.sort(Comparator.comparingInt(o -> o.get(1)));

        TreeMap<Integer, Integer> dp = new TreeMap<>();

        int N = offers.size();

        for (int i = 0; i < N; i++) {

            List<Integer> item = offers.get(i);

            int start = item.get(0);

            int end = item.get(1);

            int price = item.get(2);

            Map.Entry<Integer, Integer> floor = dp.floorEntry(start - 1);

            int f = floor == null ? 0 : floor.getValue();

            int v = price + f;

            Map.Entry<Integer, Integer> f2 = dp.floorEntry(end);

            if (f2 != null && f2.getValue() >= v)
                continue;

            dp.put(end, v);
        }

        return dp.lastEntry().getValue();
    }

    public static int right(int n, List<List<Integer>> offers) {

        offers.sort((o1, o2) -> {
            if (o1.get(0).equals(o2.get(0)))
                return o1.get(1) - o2.get(1);
            return o1.get(0) - o2.get(0);
        });


        TreeMap<Integer, Integer> dp = new TreeMap<>();

        int max = -1;

        for (int i = 0; i < offers.size(); i++) {

            List<Integer> item = offers.get(i);

            Integer start = item.get(0);

            Integer end = item.get(1);

            Integer price = item.get(2);

            Map.Entry<Integer, Integer> floor = dp.floorEntry(start - 1);

            int case1 = 0;

            if (floor != null)
                case1 = floor.getValue();

            Map.Entry<Integer, Integer> endFloor = dp.floorEntry(end);

            int best = case1 + price;

            if (endFloor == null || best > endFloor.getValue()) {

                dp.put(end, best);

                max = Math.max(
                        max,
                        best
                );

                int key = end + 1;
                Map.Entry<Integer, Integer> ceil;
                while ((ceil = dp.ceilingEntry(key)) != null) {
                    if (ceil.getValue() <= best) dp.remove(ceil.getKey());
                    key = ceil.getKey() + 1;
                }

            }


        }

        return max;
    }

}
