package com.zzzj.contest.week331;


import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zzzj
 * @create 2023-08-21 18:04
 */
public class Leet2561 {

    public static void main(String[] args) {

        System.out.println(minCost(new int[]{3, 3, 3, 3, 4}, new int[]{1, 1, 4, 4, 4}));
        System.out.println(right(new int[]{3, 3, 3, 3, 4}, new int[]{1, 1, 4, 4, 4}));

        for (int i = 0; i < 10000; i++) {
            int K = 500;
            ArrayCopyIterator a1 = ArrayCopyIterator.fromArray(ArrayUtil.generateArray(K, 1, 500));
            ArrayCopyIterator a2 = ArrayCopyIterator.fromArray(ArrayUtil.generateArray(K, 1, 500));

            long r1 = minCost(a1.next(), a2.next());
            long r2 = right(a1.next(), a2.next());

            if (r2 != r1) {
                System.out.println(Arrays.toString(ArrayUtil.sort(a1.next())));
                System.out.println(Arrays.toString(ArrayUtil.sort(a2.next())));
                System.out.println("minCost(a1.next(), a2.next()) = " + minCost(a1.next(), a2.next()));
                System.out.println("right(a1.next(), a2.next()) = " + right(a1.next(), a2.next()));
                return;
            }
        }

    }

    public static long minCost(int[] basket1, int[] basket2) {

        TreeMap<Integer, Integer> m1 = new TreeMap<>();

        TreeMap<Integer, Integer> m2 = new TreeMap<>();

        for (int num : basket1)
            m1.put(num, m1.getOrDefault(num, 0) + 1);

        for (int num : basket2)
            m2.put(num, m2.getOrDefault(num, 0) + 1);


        TreeMap<Integer, Integer> d1 = new TreeMap<>();
        TreeMap<Integer, Integer> d2 = new TreeMap<>();

        int c = 0;

        for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
            Integer cnt = m2.getOrDefault(entry.getKey(), 0);

            int diff = entry.getValue() - cnt;

            if (Math.abs(diff) % 2 != 0)
                return -1;
            if (diff > 0) {
                c += diff / 2;
                d1.put(entry.getKey(), diff / 2);
            } else if (diff < 0)
                d2.put(entry.getKey(), (-diff) / 2);

        }

        for (Map.Entry<Integer, Integer> entry : m2.entrySet()) {
            if (!m1.containsKey(entry.getKey())) {
                if (entry.getValue() % 2 != 0)
                    return -1;
                d2.put(entry.getKey(), entry.getValue() / 2);
            }
        }

        long ans = 0;

        for (int i = 0; i < c; i++) {
            // case1
            int l = Math.min(
                    d1.firstKey(),
                    d2.firstKey()
            );

            int p = m1.firstKey() + m2.firstKey();

            if (l <= p) {
                ans += l;

                if (l == d1.firstKey()) {
                    deduce(d1, d1.firstKey());
                    deduce(d2, d2.lastKey());

                    // m1 + lowEntry
                    reduce(m2, l);
                    // m1 - lowEntry
                    deduce(m1, l);
                } else {
                    deduce(d2, d2.firstKey());
                    deduce(d1, d1.lastKey());

                    // m1 + lowEntry
                    reduce(m1, l);
                    // m2 - lowEntry
                    deduce(m2, l);
                }

            } else {
                ans += p;

                Integer ld1 = d1.lastKey();
                Integer ld2 = d2.lastKey();

                deduce(d1, ld1);
                deduce(d2, ld2);

                reduce(m1, ld2);
                reduce(m2, ld1);
            }

        }

        return ans;
    }


    public static void reduce(Map<Integer, Integer> map, Integer key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public static void deduce(Map<Integer, Integer> map, Integer key) {
        Integer old = map.get(key);
        if (old <= 1)
            map.remove(key);
        else
            map.put(key, old - 1);
    }


    public static long right(int[] basket1, int[] basket2) {
        Function<int[], Map<Integer, Integer>> fun = arr -> Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
        ;
        Map<Integer, Integer> cnt1 = fun.apply(basket1), cnt2 = fun.apply(basket2);
        List<Integer> all = Stream.concat(cnt1.keySet().stream(), cnt2.keySet().stream()).distinct().collect(Collectors.toList());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int item : all) {
            int sub = Math.abs(cnt1.getOrDefault(item, 0) - cnt2.getOrDefault(item, 0));
            if ((sub & 1) == 1) {
                return -1;
            }
            IntStream.generate(() -> item).limit(sub / 2).forEach(minHeap::offer);
        }
        int min = all.stream().mapToInt(Integer::valueOf).min().orElse(0) * 2;
        return IntStream.range(0, minHeap.size() / 2).boxed().reduce(0L,
                (ans, e) -> ans + Math.min(min, minHeap.poll()), Long::sum);
    }

}
