package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.CopyableIterator;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-11-02 11:20
 */
public class Leet1353 {

    public static void main(String[] args) {

        System.out.println(maxEvents(LeetUtils.convertInts("[[1, 4], [1, 2], [1, 4], [3, 4], [3, 6], [2, 3], [1, 4]]")));

//        System.exit(0);

        for (int i = 0; i < 100000; i++) {
            int N = LeetUtils.random.nextInt(100) + 1;

            int[][] events = new int[N][2];

            for (int j = 0; j < N; j++) {
                events[j][0] = LeetUtils.random.nextInt(100) + 1;
                events[j][1] = LeetUtils.random.nextInt(100) + 1 + events[j][0];
            }

            CopyableIterator<int[][]> iterator = new CopyableIterator<>(events, ArrayUtil::copy);

            if (maxEvents(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.deepToString(iterator.next()));
                System.out.println("MyAns : " + maxEvents(iterator.next()));
                System.out.println("Right : " + right(iterator.next()));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int maxEvents(int[][] events) {
        int N = events.length;

        int ans = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(N, Comparator.comparingInt(o -> o[1]));

        int min = Integer.MAX_VALUE;

        int max = Integer.MIN_VALUE;

        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] event : events) {
            int start = event[0];

            min = Math.min(min, event[0]);
            max = Math.max(max, event[1]);

            map.computeIfAbsent(start, integer -> new ArrayList<>())
                    .add(event);
        }

        for (int i = min; i <= max; i++) {
            if (map.containsKey(i)){
                queue.addAll(map.get(i));
            }

            while (!queue.isEmpty() && i > queue.peek()[1]) {
                queue.remove();
            }

            if (!queue.isEmpty()) {
                queue.remove();
                ans++;
            }

        }

        return ans;
    }

    public static int right(int[][] events) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(events, (first, second) -> first[1] == second[1] ?
                first[0] - second[0] : first[1] - second[1]);

        for (int[] event : events) {
            for (int i = event[0]; i <= event[1]; i++)
                if (set.add(i)) break;
        }
        return set.size();
    }

}
