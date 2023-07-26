package com.zzzj.contest.dweek110;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Leet2744 {

    public static void main(String[] args) {

        System.out.println(maximumNumberOfStringPairs(new String[]{"cd", "ac", "dc", "ca", "zz"}));

        System.out.println(maximumNumberOfStringPairs(new String[]{"ab", "ba", "cc"}));

        System.out.println(maximumNumberOfStringPairs(new String[]{"aa", "ab"}));

    }

    public static int maximumNumberOfStringPairs(String[] words) {

        Set<String> set = new HashSet<>(Arrays.asList(words));

        int ans = 0;

        for (String word : words) {

            if (!set.contains(word)) continue;

            StringBuilder reversal = new StringBuilder(word.length());

            for (int i = word.length() - 1; i >= 0; i--) {
                reversal.append(word.charAt(i));
            }

            String rs = reversal.toString();

            if (rs.equals(word)) continue;

            if (set.remove(rs)) {
                set.remove(word);
                ans++;
            }
        }

        return ans;
    }

}
