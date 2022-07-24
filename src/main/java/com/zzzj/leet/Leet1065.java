package com.zzzj.leet;

import com.zzzj.util.Unresolved;

import java.util.ArrayList;

/**
 * @author Zzzj
 * @create 2022-07-23 23:34
 */
@Unresolved
public class Leet1065 {


    public static int[][] indexPairs(String text, String[] words) {
        int N = words.length;

        ArrayList<int[]> list = new ArrayList<>(words.length);

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int start = 0;
            while (start < text.length()) {
                int index = text.indexOf(word, start);
                if (index < 0) {
                    break;
                }
                list.add(new int[]{index, index + word.length() - 1});
                start++;
            }
        }

        int[][] ans = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

}
