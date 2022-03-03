package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-02-20 14:29
 */
public class Leet1839 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(100, "aeiou");
            if (longestBeautifulSubstring(str) != right(str)) {
                System.out.println(str);
                System.out.println(longestBeautifulSubstring(str));
                return;
            }
        }
    }

    public static int longestBeautifulSubstring(String word) {
        int l = 0;
        int ans = 0;

        while (l < word.length() && word.charAt(l) != 'a') {
            l++;
        }

        if (word.length() - l < 5) {
            return 0;
        }

        int r = l + 1;

        char[][] allowed = new char[26][];

        allowed[0] = new char[]{'a', 'e'};
        allowed['e' - 'a'] = new char[]{'e', 'i'};
        allowed['i' - 'a'] = new char[]{'i', 'o'};
        allowed['o' - 'a'] = new char[]{'o', 'u'};
        allowed['u' - 'a'] = new char[]{'u'};

        while (r < word.length()) {
            char previous = word.charAt(r - 1);

            char cur = word.charAt(r);

            // l和r跳到下一个a

            boolean contains = false;
            for (char c : allowed[previous - 'a']) {
                if (c == cur) {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                while (r < word.length() && word.charAt(r) != 'a') {
                    r++;
                }
                l = r;
            } else if (cur == 'u') {
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }


        return ans;
    }

    public static int right(String word) {
        int ans = 0, type = 1, len = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) >= word.charAt(i - 1)) len++; //更新当前字符串长度
            if (word.charAt(i) > word.charAt(i - 1)) type++; //更新当前字符种类
            if (word.charAt(i) < word.charAt(i - 1)) {
                type = 1;
                len = 1;
            } //当前字符串不美丽，从当前字符重新开始
            if (type == 5) ans = Math.max(ans, len);  //更新最大字符串
        }
        return ans;
    }


}
