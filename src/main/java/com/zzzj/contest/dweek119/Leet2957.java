package com.zzzj.contest.dweek119;

public class Leet2957 {

    public static int removeAlmostEqualCharacters(String word) {

        int N = word.length();

        int ans = 0;

        for (int i = 0; i < N - 1; i++) {
            if (word.charAt(i) == word.charAt(i + 1) )
                ans++;
        }

        return ans;
    }

}
