package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-12 19:44
 */
public class Leet1405 {

    public static void main(String[] args) {
        int n = 100;

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
                System.out.println(right(a, b, c));
                return;
            }
        }
        System.out.println("ok");
    }

    public static String longestDiverseString(int a, int b, int c) {
        StringBuilder builder = new StringBuilder(a + b + c);

        PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> o2.freq - o1.freq);

        if (a > 0)
            queue.add(new Pair(a, 'a'));
        if (b > 0)
            queue.add(new Pair(b, 'b'));
        if (c > 0)
            queue.add(new Pair(c, 'c'));


        int repeat = 0;
        char lastChar = 'x';

        while (!queue.isEmpty()) {

            Pair max = queue.remove();
            builder.append(max.ch);
            max.freq--;
            if (max.freq > 0) {
                queue.add(max);
            }

            if (max.ch == lastChar) {
                repeat++;
            } else {
                repeat = 1;
                lastChar = max.ch;
            }

            if (repeat == 2) {
                if (queue.isEmpty()) {
                    break;
                }
                if (queue.peek().ch == lastChar) {
                    Pair remove = queue.remove();
                    if (queue.isEmpty()) {
                        break;
                    }
                    lastChar = queue.peek().ch;
                    repeat = 1;
                    builder.append(lastChar);
                    queue.peek().freq -= 1;
                    if (queue.peek().freq == 0) {
                        queue.remove();
                    }
                    queue.add(remove);
                }
            }

        }


        return builder.toString();
    }

    static class Pair {
        int freq;
        char ch;

        public Pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
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


}
