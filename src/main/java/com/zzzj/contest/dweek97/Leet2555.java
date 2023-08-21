package com.zzzj.contest.dweek97;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-08-10 14:30
 */
public class Leet2555 {

    public static void main(String[] args) {

        System.out.println(maximizeWin(new int[]{1, 1, 2, 2, 3, 3, 5}, 2));

    }

    public static int maximizeWin(int[] prizePositions, int k) {

        TreeMap<Integer, Integer> rec = new TreeMap<>();

        for (int prizePosition : prizePositions) {
            rec.put(prizePosition, rec.getOrDefault(prizePosition, 0) + 1);
        }

        int N = rec.size();

        TreeMap<Integer, Integer> win = new TreeMap<>();

        Map<Integer, Integer> sumMap = new HashMap<>();

        Iterator<Map.Entry<Integer, Integer>> iterator = rec.entrySet().iterator();

        int sum = 0;

        while (iterator.hasNext()) {

            Map.Entry<Integer, Integer> curr = iterator.next();

            sum += curr.getValue();

            Map.Entry<Integer, Integer> floor = rec.floorEntry(curr.getKey() - k - 1);

            if (floor != null)
                win.put(curr.getKey(), sum - sumMap.get(floor.getKey()));
            else
                win.put(curr.getKey(), sum);

            sumMap.put(curr.getKey(), sum);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(N, (o1, o2) -> win.get(o2) - win.get(o1));

        queue.addAll(win.keySet());

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(win.entrySet());

        int ans = win.get(queue.peek());

        for (int i = 0; i < entries.size() && !queue.isEmpty(); i++) {

            Map.Entry<Integer, Integer> entry = entries.get(i);

            int end = entry.getKey() + k;

            while (!queue.isEmpty() && queue.peek() <= end) {
                queue.remove();
            }

            if (!queue.isEmpty()) {
                ans = Math.max(ans, entry.getValue() + win.get(queue.peek()));
            }

        }

        return ans;
    }

    public static int solution2(int[] prizePositions, int k) {

        return -1;
    }

}
