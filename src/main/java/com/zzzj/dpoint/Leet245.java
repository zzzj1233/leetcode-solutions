package com.zzzj.dpoint;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-04-21 18:54
 */
public class Leet245 {

    public static void main(String[] args) {
//        System.out.println(shortestWordDistance(new String[]{"x", "ftorxx", "x", "adxob", "oojcnqqvx", "cvi", "m", "x", "x", "mctohft"}, "x", "x"));
        int N = 20000;

        for (; ; ) {
            int wordLength = LeetUtils.random.nextInt(N) + 10;

            String[] wordsDict = new String[N];

            for (int i = 0; i < N; i++) {
                wordsDict[i] = LeetUtils.randomString(LeetUtils.random.nextInt(10) + 1, false);
            }

            int idx1 = LeetUtils.random.nextInt(N);

            int idx2 = LeetUtils.random.nextInt(N);

            while (idx2 == idx1) {
                idx2 = LeetUtils.random.nextInt(N);
            }
            if (shortestWordDistance(wordsDict, wordsDict[idx1], wordsDict[idx2]) != right(wordsDict, wordsDict[idx1], wordsDict[idx2])) {
                System.out.println(Arrays.stream(wordsDict)
                        .map(s -> "\"" + s + "\"")
                        .collect(Collectors.joining(",", "[", "]")));
                System.out.println(wordsDict[idx1]);
                System.out.println(wordsDict[idx2]);
                System.out.println("error");
                return;
            }
        }

    }

    public static int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int w1 = -1;
        int w2 = -1;

        int ans = Integer.MAX_VALUE;

        boolean isEqual = word1.equals(word2);

        for (int i = 0; i < wordsDict.length; i++) {

            if (wordsDict[i].equals(word1)) {
                if (isEqual && w1 != -1) {
                    w2 = w1;
                }
                w1 = i;
                if (w2 != -1) {
                    ans = Math.min(ans, w1 - w2);
                }
            } else if (wordsDict[i].equals(word2)) {
                w2 = i;
                if (w1 != -1) {
                    ans = Math.min(ans, w2 - w1);
                }
            }
        }

        return ans;
    }

    public static int right(String[] wordsDict, String word1, String word2) {
        int idx = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1) || wordsDict[i].equals(word2)) {
                if (idx != -1 && (!wordsDict[i].equals(wordsDict[idx]) || word1.equals(word2))) {
                    minDist = Math.min(minDist, Math.abs(i - idx));
                }
                idx = i;
            }
        }

        return minDist;
    }


}
