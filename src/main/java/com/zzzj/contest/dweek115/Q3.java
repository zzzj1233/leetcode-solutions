package com.zzzj.contest.dweek115;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(getWordsInLongestSubsequence(3, new String[]{"bab", "dab", "cab"}, new int[]{1, 2, 2}));

        System.out.println(getWordsInLongestSubsequence(4, new String[]{"a", "b", "c", "d"}, new int[]{1, 2, 3, 4}));

        System.out.println(getWordsInLongestSubsequence(3, new String[]{"ccd", "bb", "ccc"}, new int[]{1, 1, 2}));

        System.out.println(getWordsInLongestSubsequence(5, new String[]{"ca", "cb", "bcd", "bb", "ddc"}, new int[]{2, 4, 2, 5, 1}));
    }

    public static List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {

        boolean[][] distance = new boolean[n][n];

        for (int i = 0; i < n; i++)
            distance[i][i] = true;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int len = words[j].length();

                if (len == words[i].length()) {
                    int diff = 0;

                    for (int k = 0; k < len; k++) {
                        if (words[j].charAt(k) != words[i].charAt(k)) {
                            diff++;
                            if (diff == 2) {
                                break;
                            }
                        }
                    }
                    if (diff == 1)
                        distance[i][j] = true;
                }
            }

        }

        int[] dp = new int[n];

        int max = 0;

        for (int i = 0; i < n; i++) {

            dp[i] = 1;

            for (int j = 0; j < i; j++) {

                if (groups[j] != groups[i] && distance[j][i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }

            max = Math.max(max, dp[i]);
        }

        if (max == 0)
            return Collections.emptyList();

        int nextDp = max;
        int excludeGroup = -1;
        int index = -1;

        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == max) {
                index = i;
                break;
            }
        }

        List<String> ans = new ArrayList<>();

        for (int i = index; i >= 0; i--) {
            if (dp[i] == nextDp && groups[i] != excludeGroup && distance[i][index]) {
                ans.add(words[i]);
                nextDp--;
                excludeGroup = groups[i];
                index = i;
            }
        }

        Collections.reverse(ans);

        return ans;
    }

}
