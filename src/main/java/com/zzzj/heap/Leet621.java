package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zzzj
 * @create 2022-03-17 15:01
 */
public class Leet621 {

    public static void main(String[] args) {
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));

        for (int i = 0; i < 10000; i++) {
            char[] chars = LeetUtils.randomUpperString(100).toCharArray();
            int n = LeetUtils.random.nextInt(chars.length);

            if (leastInterval(chars, n) != right(chars, n)) {
                System.out.println(LeetUtils.charsToLeetCode(chars));
                System.out.println(n);
                System.out.println(leastInterval(chars, n));
                return;
            }
        }

    }

    public static int leastInterval(char[] tasks, int n) {

        int[] table = new int[126];

        for (char task : tasks) {
            table[task]++;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(table.length, (o1, o2) -> o2[1] - o1[1]);

        for (int i = 0; i < table.length; i++) {
            if (table[i] > 0) {
                queue.add(new int[]{i, table[i]});
            }
        }

        int ans = 0;

        LinkedList<int[]> temp = new LinkedList<>();

        int[] latest = new int[table.length];

        while (!queue.isEmpty() || !temp.isEmpty()) {
            int[] item;
            if (queue.isEmpty()) {
                item = temp.removeFirst();
                int count = item[1];
                char c = (char) item[0];
                int latestVisit = latest[c];
                ans += n - (ans - latestVisit) + 1;
            } else {
                if (!temp.isEmpty()) {
                    int[] first = temp.peekFirst();
                    char c = (char) first[0];
                    int latestVisit = latest[c];
                    if (n == 0 || (ans - latestVisit) % (n + 1) == 0) {
                        queue.add(temp.removeFirst());
                    }
                }
                item = queue.remove();
            }
            int count = item[1];
            if (count - 1 > 0) {
                item[1] = count - 1;
                temp.add(item);
                latest[item[0]] = ans;
            }
            ans++;
        }

        return ans;
    }

    public static int right(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // 任务总数
        int m = freq.size();
        List<Integer> nextValid = new ArrayList<Integer>();
        List<Integer> rest = new ArrayList<Integer>();
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            nextValid.add(1);
            rest.add(value);
        }

        int time = 0;
        for (int i = 0; i < tasks.length; ++i) {
            ++time;
            int minNextValid = Integer.MAX_VALUE;
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0) {
                    minNextValid = Math.min(minNextValid, nextValid.get(j));
                }
            }
            time = Math.max(time, minNextValid);
            int best = -1;
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                    if (best == -1 || rest.get(j) > rest.get(best)) {
                        best = j;
                    }
                }
            }
            nextValid.set(best, time + n + 1);
            rest.set(best, rest.get(best) - 1);
        }

        return time;
    }


}
