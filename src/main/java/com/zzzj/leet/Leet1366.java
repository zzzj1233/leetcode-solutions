package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;

public class Leet1366 {

    public static void main(String[] args) {
        System.out.println(rankTeams(new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"}));
        System.out.println(rankTeams(new String[]{"BCA", "CAB", "CBA", "ABC", "ACB", "BAC"}));
        System.out.println(rankTeams(new String[]{"WXYZ", "XYZW"}));
    }

    public static String rankTeams(String[] votes) {
        int M = votes[0].length();

        // [i][j]
        // i = 第I个字符, A - Z
        // j = 第几名, 1 - M
        int[][] vote = new int[26][M];

        Map<Character, int[]> counter = new HashMap<>();

        for (String s : votes) {
            for (int i = 0; i < M; i++) {
                char c = s.charAt(i);
                if (!counter.containsKey(c)) {
                    counter.put(c, new int[M]);
                }
                counter.get(c)[i]++;
            }
        }

        return counter.entrySet().stream().sorted((o1, o2) -> {
            int compare = compareRank(o1.getValue(), o2.getValue());
            if (compare == 0) {
                return Character.compare(o1.getKey(), o2.getKey());
            }
            return compare;
        }).map(Map.Entry::getKey).map(Objects::toString).collect(Collectors.joining());
    }

    public static int compareRank(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[i]) {
                continue;
            }
            if (A[i] > B[i]) {
                return -1;
            }
            return 1;
        }
        return 0;
    }

}
