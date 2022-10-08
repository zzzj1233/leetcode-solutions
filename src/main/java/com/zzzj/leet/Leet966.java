package com.zzzj.leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-10-08 18:07
 */
public class Leet966 {

    private final Set<String> wordSet = new HashSet<>();
    private final Map<String, String> wordPatternMap = new HashMap<>();

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /**
     * 掩盖元音，将单词中所有元音替换为通配符
     */
    private String maskVowel(String word) {
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (isVowel(letters[i])) {
                letters[i] = '*';
            }
        }
        return String.valueOf(letters);
    }

    /**
     * 查询对应的正确单词
     */
    private String search(String query) {
        // 精确匹配
        if (wordSet.contains(query)) return query;
        // 忽略大小写
        query = query.toLowerCase();
        if (wordPatternMap.containsKey(query)) {
            return wordPatternMap.get(query);
        }
        // 忽略元音差异
        query = maskVowel(query);
        return wordPatternMap.getOrDefault(query, "");
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        for (String word : wordlist) {
            // 保存单词原型，以满足优先进行精确匹配
            wordSet.add(word);
            // 保存小写单词，以满足忽略大小写的次优匹配
            String wordLC = word.toLowerCase();
            wordPatternMap.putIfAbsent(wordLC, word);
            // 保存小写及元音被通配符替换的单词，以满足忽略元音差异匹配
            wordPatternMap.putIfAbsent(maskVowel(wordLC), word);
        }
        for (int i = 0; i < queries.length; i++) {
            queries[i] = search(queries[i]);
        }
        return queries;
    }


}
