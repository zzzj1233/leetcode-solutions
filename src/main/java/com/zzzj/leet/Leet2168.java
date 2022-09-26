package com.zzzj.leet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-09-26 16:56
 */
public class Leet2168 {

    public static void main(String[] args) {
        System.out.println(equalDigitFrequency("12321"));
    }

    public static int equalDigitFrequency(String s) {
        int N = s.length();

        int ans = 0;

        Set<String> exists = new HashSet<>();

        for (int i = 0; i < N; i++) {

            int[] bucket = new int[26];

            for (int j = i; j < N; j++) {
                bucket[s.charAt(j) - '0']++;
                if (isSameFrequency(bucket) && exists.add(s.substring(i, j + 1))) {
                    ans++;
                }
            }

        }

        return ans;
    }

    public static boolean isSameFrequency(int[] bucket) {
        int count = -1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                if (count == -1) {
                    count = bucket[i];
                } else if (bucket[i] != count) {
                    return false;
                }
            }
        }
        return count != -1;
    }

}
