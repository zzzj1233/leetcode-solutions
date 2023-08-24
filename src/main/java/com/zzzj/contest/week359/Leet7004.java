package com.zzzj.contest.week359;

import java.util.Arrays;
import java.util.List;

public class Leet7004 {

    public static void main(String[] args) {

        System.out.println(isAcronym(Arrays.asList("alice", "bob", "charlie"), "abc"));

        System.out.println(isAcronym(Arrays.asList("never", "gonna", "give", "up", "on", "you"), "ngguoy"));

    }

    public static boolean isAcronym(List<String> words, String s) {

        if (s.length() != words.size()) return false;

        int N = s.length();

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) != words.get(i).charAt(0))
                return false;
        }

        return true;
    }

}
