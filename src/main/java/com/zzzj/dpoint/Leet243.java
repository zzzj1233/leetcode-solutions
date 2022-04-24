package com.zzzj.dpoint;

/**
 * @author zzzj
 * @create 2022-04-21 18:43
 */
public class Leet243 {

    public static int shortestDistance(String[] wordsDict, String word1, String word2) {
        int w1 = -1;
        int w2 = -1;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < wordsDict.length; i++) {
            String word = wordsDict[i];
            if (word.equals(word1)) {
                w1 = i;
                if (w2 != -1) {
                    ans = Math.min(ans, Math.abs(w1 - w2));
                }
            } else if (word.equals(word2)) {
                w2 = i;
                if (w1 != -1) {
                    ans = Math.min(ans, Math.abs(w1 - w2));
                }
            }
        }

        return ans;
    }

}
