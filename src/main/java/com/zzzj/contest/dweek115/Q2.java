package com.zzzj.contest.dweek115;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(getWordsInLongestSubsequence(3, new String[]{"e", "a", "b"}, new int[]{0, 0, 1}));

        System.out.println(getWordsInLongestSubsequence(4, new String[]{"a", "b", "c", "d"}, new int[]{1, 0, 1, 1}));

    }

    public static List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {

        int[] dp = new int[n + 1];

        int max = 0;

        for (int i = 1; i <= n; i++) {

            dp[i] = 1;

            for (int j = 1; j < i; j++) {
                // 不相同
                if ((groups[j - 1] ^ groups[i - 1]) == 1) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(dp[i], max);
        }

        int index = -1;

        for (int i = 1; i <= n; i++) {
            if (dp[i] == max) {
                index = i - 1;
                break;
            }
        }

        if (index == -1)
            return Collections.emptyList();

        List<String> ans = new ArrayList<>();

        int expectDp = max;
        int expectGroup = groups[index];

        for (int i = index; i >= 0; i--) {
            if (dp[i + 1] == expectDp && groups[i] == expectGroup) {
                ans.add(words[i]);
                expectDp--;
                expectGroup ^= 1;
            }
        }

        Collections.reverse(ans);

        return ans;
    }

}
