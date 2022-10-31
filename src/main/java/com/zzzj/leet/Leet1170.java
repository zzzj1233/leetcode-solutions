package com.zzzj.leet;


/**
 * @author Zzzj
 * @create 2022-09-25 22:32
 */
public class Leet1170 {

    public static int[] numSmallerByFrequency(String[] queries, String[] words) {
        // 找出words字符串数组中有几个字符串w使得f(queries[i])<f(w)。

        int[] minCount = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            minCount[i] = getMinCharCount(words[i]);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int count = getMinCharCount(queries[i]);

            for (int j = 0; j < minCount.length; j++) {
                if (count < minCount[j]) {
                    ans[i]++;
                }
            }

        }

        return ans;
    }

    public static int getMinCharCount(String word) {
        int[] bucket = new int[26];
        for (int i = 0; i < word.length(); i++) {
            bucket[word.charAt(i) - 'a']++;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                return bucket[i];
            }
        }

        return 0;
    }

}
