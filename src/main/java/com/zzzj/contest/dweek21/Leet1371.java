package com.zzzj.contest.dweek21;

import cn.hutool.core.lang.id.NanoId;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-09 12:54
 */
public class Leet1371 {

    public static void main(String[] args) {

        System.out.println(findTheLongestSubstring("eleetminicoworoep"));

        System.out.println(findTheLongestSubstring("leetcodeisgreat"));

        System.out.println(findTheLongestSubstring("bcbcbc"));

    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static int findTheLongestSubstring(String s) {

        int N = s.length();

        int[] preSum = new int[N + 1];

        Map<Integer, Integer> rec = new HashMap<>();

        int ans = 0;

        rec.put(0, 0);

        for (int i = 1; i <= N; i++) {

            if (isVowel(s.charAt(i - 1))) {
                preSum[i] = preSum[i - 1] ^ (1 << (s.charAt(i - 1) - 'a'));
            } else {
                preSum[i] = preSum[i - 1];
            }

            if (rec.containsKey(preSum[i])) {
                ans = Math.max(ans, i - rec.get(preSum[i]));
            } else {
                rec.put(preSum[i], i);
            }
        }

        return ans;
    }

}
