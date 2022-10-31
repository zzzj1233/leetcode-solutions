package com.zzzj.greedy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Leet1338 {

    public static int minSetSize(int[] arr) {

        int N = arr.length;

        Map<Integer, Integer> count = new HashMap<>(N);

        for (int i : arr) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }


        int target = N / 2;

        List<Integer> list = count.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .sorted((o1, o2) -> o2 - o1)
                .collect(Collectors.toList());

        int sum = 0;

        int ans = 0;

        for (Integer c : list) {
            sum += c;
            ans++;
            if (sum >= target){
                return ans;
            }
        }

        return N;
    }

}
