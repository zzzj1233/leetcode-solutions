package com.zzzj.leet;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-06-29 17:37
 */
public class Leet767 {

    public static void main(String[] args) {
        System.out.println(reorganizeString("abbabbaaab"));
    }

    // "aabbcc"
    // "acacb"
    //
    public static String reorganizeString(String s) {
        int[] bucket = new int[26];

        int max = 0;

        int N = s.length();

        for (int i = 0; i < N; i++) {
            int index = s.charAt(i) - 'a';
            bucket[index]++;
            max = Math.max(max, bucket[index]);
        }

        if (max > ((N & 1) == 1 ? ((N + 1) / 2) : (N / 2))) {
            return "";
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(26, (o1, o2) -> o2[1] - o1[1]);

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                queue.add(new int[]{i, bucket[i]});
            }
        }

        StringBuilder builder = new StringBuilder(N);


        while (queue.size() >= 2) {
            int[] first = queue.remove();
            int[] second = queue.remove();

            builder.append(((char) (first[0] + 'a')));
            builder.append(((char) (second[0] + 'a')));

            if (first[1] > 1) {
                queue.add(new int[]{first[0], first[1] - 1});
            }
            if (second[1] > 1) {
                queue.add(new int[]{second[0], second[1] - 1});
            }
        }

        if (!queue.isEmpty()) {
            builder.append(((char) (queue.remove()[0] + 'a')));
        }

        return builder.toString();
    }

}
