package com.zzzj.greedy;

import java.util.PriorityQueue;

public class Leet2182 {

    public static void main(String[] args) {
        System.out.println(repeatLimitedString("aababab", 2));
    }

    public static String repeatLimitedString(String s, int repeatLimit) {

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                queue.add(new int[]{i, count[i]});
            }
        }

        StringBuilder builder = new StringBuilder(s.length());

        while (queue.size() >= 2) {
            int[] item = queue.remove();
            int appendCount = Math.min(item[1], repeatLimit);
            for (int i = 0; i < appendCount; i++) {
                builder.append((char) (item[0] + 'a'));
            }
            item[1] -= appendCount;

            if (item[1] > 0) {
                int[] second = queue.peek();

                builder.append((char) (second[0] + 'a'));

                second[1] -= 1;

                if (second[1] == 0) {
                    queue.remove();
                }

                queue.add(item);
            }
        }

        int[] last = queue.remove();

        int end = Math.min(last[1], repeatLimit);

        for (int i = 0; i < end; i++) {
            builder.append((char) (last[0] + 'a'));
        }

        return builder.toString();
    }

}
