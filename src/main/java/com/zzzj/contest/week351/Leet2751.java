package com.zzzj.contest.week351;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-07-24 14:14
 */
public class Leet2751 {

    public static void main(String[] args) {

        System.out.println(survivedRobotsHealths(new int[]{5, 4, 3, 2, 1}, new int[]{2, 17, 9, 15, 10}, "RRRRR"));

        System.out.println(survivedRobotsHealths(new int[]{3, 5, 2, 6}, new int[]{10, 10, 15, 12}, "RLRL"));

        System.out.println(survivedRobotsHealths(new int[]{1, 2, 5, 6}, new int[]{10, 10, 11, 11}, "RLRL"));

    }

    public static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        List<int[]> ans = new ArrayList<>();

        LinkedList<int[]> queue = new LinkedList<>();

        int N = positions.length;

        int[][] combined = new int[N][4];

        final int TO_LEFT = 0;
        final int TO_RIGHT = 1;

        for (int i = 0; i < N; i++) {
            combined[i][0] = positions[i];
            combined[i][1] = healths[i];
            combined[i][2] = directions.charAt(i) == 'L' ? TO_LEFT : TO_RIGHT;
            combined[i][3] = i;
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        for (int i = 0; i < N; i++) {

            int pos = combined[i][3];

            int heal = combined[i][1];

            boolean toLeft = combined[i][2] == TO_LEFT;

            if (toLeft) {
                if (queue.isEmpty()) {
                    ans.add(new int[]{pos, heal});
                } else {
                    while (!queue.isEmpty() && heal > 0) {

                        int[] last = queue.peekLast();

                        if (last[1] <= heal) {
                            queue.removeLast();
                            if (last[1] == heal)
                                heal = 0;
                            else
                                heal -= 1;
                        } else {
                            last[1] -= 1;
                            heal = 0;
                        }
                    }

                    if (heal > 0) {
                        ans.add(new int[]{pos, heal});
                    }
                }
            } else {
                // 去右边的
                queue.add(new int[]{pos, heal});
            }

        }

        while (!queue.isEmpty()) {
            int[] item = queue.removeFirst();
            ans.add(item);
        }

        return ans.stream()
                .sorted(Comparator.comparingInt(o -> o[0]))
                .map(ints -> ints[1])
                .collect(Collectors.toList());
    }

}
