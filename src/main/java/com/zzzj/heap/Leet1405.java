package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-12 19:44
 */
public class Leet1405 {

    public static void main(String[] args) {
        int n = 10;

        System.out.println(longestDiverseString(2, 8, 2));

        // bbabbabbaabb
        // bbaabbabbab
        for (int i = 0; i < 10000; i++) {
            int a = LeetUtils.random.nextInt(n);
            int b = LeetUtils.random.nextInt(n);
            int c = LeetUtils.random.nextInt(n);
            if (longestDiverseString(a, b, c).length() != right(a, b, c).length()) {
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                System.out.println(longestDiverseString(a, b, c));
                return;
            }
        }
    }

    public static String longestDiverseString(int a, int b, int c) {
        // 贪心
        StringBuilder builder = new StringBuilder();

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> ((int[]) o)[1]).reversed());

        if (a > 0) {
            queue.add(new int[]{'a', a});
        }
        if (b > 0) {
            queue.add(new int[]{'b', b});
        }
        if (c > 0) {
            queue.add(new int[]{'c', c});
        }

        int[] item;

        int[] oldItem = null;

        while (!queue.isEmpty()) {
            item = queue.remove();

            if (oldItem != null) {
                queue.add(oldItem);
            }

            char alpha = (char) item[0];

            int count = item[1];

            int loop;
            if (builder.length() >= 2 && !queue.isEmpty()) {
                loop = count <= (queue.peek()[1] >> 1) ? 1 : Math.min(2, count);
            } else {
                loop = Math.min(2, count);
            }

            for (int i = 0; i < loop; i++) {
                builder.append(alpha);
            }

            int remain = count - loop;

            if (queue.isEmpty()) {
                return builder.toString();
            }

            // 重新放回队列
            if (remain > 0) {
                oldItem = item;
                oldItem[1] = remain;
            } else {
                oldItem = null;
            }

        }


        return builder.toString();
    }

    public static String right(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        Pair[] arr = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};

        while (true) {
            Arrays.sort(arr, (p1, p2) -> p2.freq - p1.freq);
            boolean hasNext = false;
            for (Pair pair : arr) {
                if (pair.freq <= 0) {
                    break;
                }
                int m = res.length();
                if (m >= 2 && res.charAt(m - 2) == pair.ch && res.charAt(m - 1) == pair.ch) {
                    continue;
                }
                hasNext = true;
                res.append(pair.ch);
                pair.freq--;
                break;
            }
            if (!hasNext) {
                break;
            }
        }

        return res.toString();
    }

    static class Pair {
        int freq;
        char ch;

        public Pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }


}
