package com.zzzj.simple;

import java.util.List;

/**
 * @author zzzj
 * @create 2023-04-12 16:45
 */
public class Leet1773 {

    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {

        int index;

        if (ruleKey.equals("type")) {
            index = 0;
        } else if (ruleKey.equals("color")) {
            index = 1;
        } else {
            index = 2;
        }

        int ans = 0;

        for (List<String> item : items) {

            if (item.get(index).equals(ruleValue)) {

                ans++;

            }

        }

        return ans;
    }

}
