package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-06-27 11:30
 */
public class Leet1408 {

    public static List<String> stringMatching(String[] words) {

        List<String> ans = new ArrayList<>(words.length);


        for (int i = 0; i < words.length; i++) {

            for (int j = 0; j < words.length; j++) {

                if (i == j) {
                    continue;
                }

                if (words[j].indexOf(words[i]) != -1) {
                    ans.add(words[i]);
                    break;
                }
            }

        }

        return ans;
    }

}
