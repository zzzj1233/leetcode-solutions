package com.zzzj.str;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2024-01-22 15:48
 */
public class Leet358 {

    public static void main(String[] args) {

        System.out.println(rearrangeString("aabbcc", 3));
//
        System.out.println(rearrangeString("aaabc", 3));

        System.out.println(rearrangeString("aaadbbcc", 2));

    }

    public static String rearrangeString(String s, int k) {

        int N = s.length();

        int[] cnt = new int[27];

        for (int i = 0; i < N; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        PriorityQueue<Integer> cntQueue = new PriorityQueue<>(27, (o1, o2) -> {
            int diff = cnt[o2] - cnt[o1];
            return diff == 0 ? o1 - o2 : diff;
        });

        for (int i = 0; i < 27; i++) {
            if (cnt[i] > 0)
                cntQueue.add(i);
        }

        LinkedList<int[]> inQueue = new LinkedList<>();

        StringBuilder builder = new StringBuilder(N);

        while (!cntQueue.isEmpty() || !inQueue.isEmpty()) {

            while (!inQueue.isEmpty() && builder.length() >= inQueue.peekFirst()[0] + k) {
                cntQueue.add(inQueue.removeFirst()[1]);
            }

            if (cntQueue.isEmpty())
                break;

            Integer first = cntQueue.remove();

            cnt[first]--;

            if (cnt[first] > 0)
                inQueue.addLast(new int[]{builder.length(), first});

            builder.append((char) (first + 'a'));
        }

        return builder.length() == N ? builder.toString() : "";
    }

}
