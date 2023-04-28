package com.zzzj.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-04-26 15:36
 */
public class Leet1717 {


    public static void main(String[] args) {

        System.out.println(
                maximumGain(
                        "aabbabkbbbfvybssbtaobaaaabataaadabbbmakgabbaoapbbbbobaabvqhbbzbbkapabaavbbeghacabamdpaaqbqabbjbababmbakbaabajabasaabbwabrbbaabbafubayaazbbbaababbaaha",
                        1926,
                        4320
                )
        );

        System.out.println(maximumGain("cdbcbbaaabab", 4, 5));

        System.out.println(maximumGain("aabbaaxybbaabb", 5, 4));


    }

    public static int maximumGain(String s, int x, int y) {

        // 删除ab得x分
        // 删除ba得y分

        int N = s.length();

        List<Character> chars = new ArrayList<>(N);

        String maxScoreStr = x >= y ? "ab" : "ba";

        String minScoreStr = maxScoreStr.equals("ab") ? "ba" : "ab";

        int maxScore = Math.max(x, y);

        int minScore = Math.min(x, y);

        int ans = 0;

        for (int i = 0; i < N; i++) {

            chars.add(s.charAt(i));

            while (match(chars, maxScoreStr)) {
                chars.remove(chars.size() - 1);
                chars.remove(chars.size() - 1);
                ans += maxScore;
            }

        }


        List<Character> chars2 = new ArrayList<>(chars.size());

        for (int i = 0; i < chars.size(); i++) {

            chars2.add(chars.get(i));

            while (match(chars2, maxScoreStr)) {
                chars2.remove(chars2.size() - 1);
                chars2.remove(chars2.size() - 1);
                ans += maxScore;
            }

            while (match(chars2, minScoreStr)) {
                chars2.remove(chars2.size() - 1);
                chars2.remove(chars2.size() - 1);
                ans += minScore;
            }

        }

        return ans;
    }

    public static boolean match(List<Character> chars, String str, int index) {
        if (index < 1) {
            return false;
        }

        return chars.get(index) == str.charAt(1) && chars.get(index - 1) == str.charAt(0);
    }

    public static boolean match(List<Character> chars, String str) {
        return match(chars, str, chars.size() - 1);
    }

}
