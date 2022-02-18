package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-02-17 15:13
 */
public class Leet425 {

    public static void main(String[] args) {

        System.out.println(wordSquares(new String[]{"area", "lead", "wall", "lady", "ball"}));

        System.out.println(wordSquares(new String[]{"abat", "baba", "atan", "atal"}));

    }

    public static List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();

        process(ans, words, new ArrayList<>(words[0].length()));

        return ans;
    }

    public static void process(List<List<String>> ans, String[] words, ArrayList<String> path) {
        if (path.size() > words[0].length()) return;

        if (path.size() > 1 && !valid(path)) {
            return;
        }

        if (path.size() == words[0].length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            path.add(word);
            process(ans, words, path);
            path.remove(path.size() - 1);
        }

    }

    public static boolean valid(ArrayList<String> path) {
        int size = path.size();

        String last = path.get(path.size() - 1);

        int checkCount = size - 1;

        // 检查第size个字母
        for (int i = 0; i < checkCount; i++) {
            if (path.get(i).charAt(size - 1) != last.charAt(i)) {
                return false;
            }
        }

        // 第二个检查一个 -> 第一个的第二个

        // 第三个检查两个 -> 第一个和第二个的第三个

        // 第四个检查三个 -> 第一二三个的第四个
        return true;
    }

}
