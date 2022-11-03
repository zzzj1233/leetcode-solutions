package com.zzzj.greedy;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-11-01 18:04
 */
public class Leet954 {

    public static void main(String[] args) {

        System.out.println(canReorderDoubled(new int[]{1, 2, 1, -8, 8, -4, 4, -4, 2, -2}));

        System.exit(0);

        for (int i = 0; i < 100000; i++) {
            int N = 100;

            int[] arr = ArrayUtil.generateArray(N, -100, 100);

            ArrayCopyIterator iterator = new ArrayCopyIterator(arr);

            if (canReorderDoubled(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println("MyAns : " + canReorderDoubled(iterator.next()));
                System.out.println("Right : " + right(iterator.next()));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static boolean canReorderDoubled(int[] arr) {

        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> {
            if (o1 < 0 && o2 < 0) {
                return o2 - o1;
            } else {
                return o1 - o2;
            }
        });

        int zeroCount = 0;

        for (int it : arr) {
            if (it == 0) {
                zeroCount++;
            } else {
                map.put(it, map.getOrDefault(it, 0) + 1);
            }
        }

        if (zeroCount % 2 != 0) {
            return false;
        }

        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> first = map.pollFirstEntry();

            int mul = first.getKey() * 2;

            Integer db = map.get(mul);
            if (db == null || db < first.getValue()) {
                return false;
            }
            if (db - first.getValue() > 0) {
                map.put(mul, db - first.getValue());
            } else {
                map.remove(mul);
            }
        }

        return true;
    }

    public static boolean right(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : arr) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        if (cnt.getOrDefault(0, 0) % 2 != 0) {
            return false;
        }

        List<Integer> vals = new ArrayList<Integer>();
        for (int x : cnt.keySet()) {
            vals.add(x);
        }
        Collections.sort(vals, (a, b) -> Math.abs(a) - Math.abs(b));

        for (int x : vals) {
            if (cnt.getOrDefault(2 * x, 0) < cnt.get(x)) { // 无法找到足够的 2x 与 x 配对
                return false;
            }
            cnt.put(2 * x, cnt.getOrDefault(2 * x, 0) - cnt.get(x));
        }
        return true;
    }


}
