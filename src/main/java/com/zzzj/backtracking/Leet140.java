package com.zzzj.backtracking;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-02-25 17:26
 */
public class Leet140 {

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();

        Set<Integer> length = wordDict.stream().map(String::length).collect(Collectors.toSet());

        int maxLength = length.stream().mapToInt(value -> value).max().getAsInt();

        dfs(s.toCharArray(), 0, ans, new HashSet<>(wordDict), length, maxLength, new LinkedList<>());

        return ans;
    }

    /**
     * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
     * 输出:["cats and dog","cat sand dog"]
     */
    public static void dfs(
            char[] chars,
            int cur,
            List<String> ans,
            Set<String> wordDict,
            Set<Integer> length,
            int maxLength,
            LinkedList<String> path
    ) {

        if (cur == chars.length) {
            ans.add(String.join(" ", path));
            return;
        }

        for (int i = cur; i < chars.length; i++) {
            int len = i - cur + 1;

            if (!length.contains(len)) {
                continue;
            }

            if (len > maxLength) {
                break;
            }

            String str = String.valueOf(chars, cur, len);

            if (!wordDict.contains(str)) {
                continue;
            }

            path.add(str);
            dfs(chars, i + 1, ans, wordDict, length, maxLength, path);
            path.removeLast();
        }

    }

}
