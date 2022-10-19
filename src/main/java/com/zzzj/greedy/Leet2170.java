package com.zzzj.greedy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Leet2170 {

    public static void main(String[] args) {
//        System.out.println(minimumOperations(new int[]{3, 1, 3, 2, 4, 3}));
//        System.out.println(minimumOperations(new int[]{1, 2, 2, 2, 2}));
        // [4,4,4,4,3,4]
        System.out.println(minimumOperations(new int[]{4, 4, 4, 4, 3, 4}));
    }

    public static int minimumOperations(int[] nums) {
        int N = nums.length;

        if (N <= 1) {
            return 0;
        }

        Map<Integer, Integer> oddMap = new HashMap<>();

        Map<Integer, Integer> evenMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (i % 2 == 0) {
                evenMap.put(num, evenMap.getOrDefault(num, 0) + 1);
            } else {
                oddMap.put(num, oddMap.getOrDefault(num, 0) + 1);
            }
        }

        List<Map.Entry<Integer, Integer>> sortedOdd = oddMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .collect(Collectors.toList());

        List<Map.Entry<Integer, Integer>> sortedEven = evenMap.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .collect(Collectors.toList());


        // firstOdd & secondOdd , firstEven & secondEven
        Map.Entry<Integer, Integer> firstOdd = sortedOdd.get(0);
        Map.Entry<Integer, Integer> secondOdd = sortedOdd.size() == 1 ? null : sortedOdd.get(1);

        Map.Entry<Integer, Integer> firstEven = sortedEven.get(0);
        Map.Entry<Integer, Integer> secondEven = sortedEven.size() == 1 ? null : sortedEven.get(1);

        if (firstOdd.getKey().equals(firstEven.getKey())) {
            if (secondOdd == null && secondEven == null) {
                return firstOdd.getValue();
            } else if (secondOdd == null) {
                return N - (firstOdd.getValue() + secondEven.getValue());
            } else if (secondEven == null) {
                return N - (firstEven.getValue() + secondOdd.getValue());
            }
            return N - Math.max(firstOdd.getValue() + secondEven.getValue(), firstEven.getValue() + secondOdd.getValue());
        }

        return N - (firstOdd.getValue() + firstEven.getValue());
    }

}
