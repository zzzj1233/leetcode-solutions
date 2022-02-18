package com.zzzj.daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2021-12-10 11:37
 */
public class Leet748 {

    /**
     * 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
     * <p>
     * 补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
     * <p>
     * 在匹配 licensePlate 中的字母时：
     * <p>
     * 忽略licensePlate 中的 数字和空格 。
     * 不区分大小写。
     * 如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
     * 例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
     * <p>
     * 请你找出并返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 最靠前的 那个。
     * spst
     * 输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
     * 输出："steps"
     * 解释：最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
     * "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
     * "steps" 包含 "t"、"p" 和两个 "s"。
     * "stripe" 缺一个 "s"。
     * "stepple" 缺一个 "s"。
     * 因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案
     * <p>
     * 输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
     * 输出："pest"
     * s
     * 解释：licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
     */
    public static void main(String[] args) {
        System.out.println(shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));
    }

    public static String shortestCompletingWord(String licensePlate, String[] words) {
        int[] table = new int[26];

        int effectiveLen = 0;

        for (int i = 0; i < licensePlate.length(); i++) {
            char c = licensePlate.charAt(i);
            int index = 0;
            if (c >= 'a' && c <= 'z') {
                index = c - 97;
                table[index]++;
                effectiveLen++;
            } else if (c >= 'A' && c <= 'Z') {
                index = c - 65;
                table[index]++;
                effectiveLen++;
            }
        }

        int finalEffectiveLen = effectiveLen;

        List<String> list = Arrays.stream(words)
                .filter(s -> s.length() >= finalEffectiveLen)
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        for (String s : list) {
            int[] copy = Arrays.copyOfRange(table, 0, table.length);

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                copy[c - 97]--;
            }

            boolean allZero = true;

            for (int i = 0; i < copy.length; i++) {
                if (copy[i] > 0) {
                    allZero = false;
                    break;
                }
            }

            if (allZero) {
                return s;
            }

        }

        return null;
    }

}
