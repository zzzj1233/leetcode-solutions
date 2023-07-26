package com.zzzj.contest.week355;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet6921 {


    public static void main(String[] args) {
        System.out.println(splitWordsBySeparator(Arrays.asList("one.two.three", "four.five", "six"), '.'));
        System.out.println(splitWordsBySeparator(Arrays.asList("$easy$", "$problem$"), '$'));
        System.out.println(splitWordsBySeparator(Arrays.asList("|||"), '|'));
    }

    public static List<String> splitWordsBySeparator(List<String> words, char separator) {

        List<String> ans = new ArrayList<>();


        for (String word : words) {

            int prev = 0;

            for (int i = 0; i < word.length(); i++) {

                if (word.charAt(i) == separator) {
                    if (i - prev > 0) ans.add(word.substring(prev, i));
                    prev = i + 1;
                }

            }

            if (word.length() - prev > 0) ans.add(word.substring(prev));

        }

        return ans;
    }

}
