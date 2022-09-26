package com.zzzj.leet;

import com.zzzj.util.Timeout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-09-16 14:50
 */
@Timeout
public class Leet1554 {

    public static void main(String[] args) {
        System.out.println(differByOne(new String[]{"ab", "cd", "yz"}));
    }

    public static boolean differByOne(String[] dict) {

        Map<String, Character> map = new HashMap<>();

        for (String s : dict) {
            int M = s.length();

            char[] chars = s.toCharArray();

            for (int i = 0; i < M; i++) {
                char c = s.charAt(i);
                chars[i] = '*';

                String newStr = String.valueOf(chars);

                if (map.containsKey(newStr)) {
                    if (map.get(newStr) != c) {
                        return true;
                    }
                } else {
                    map.put(newStr, c);
                }

                chars[i] = c;
            }

        }

        return false;
    }

}
