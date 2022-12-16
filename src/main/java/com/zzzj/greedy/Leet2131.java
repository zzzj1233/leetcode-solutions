package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.CopyableIterator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-11-03 17:22
 */
public class Leet2131 {

    public static Map<String, Integer> count(String[] arr) {
        Map<String, Integer> ret = new HashMap<>();
        for (String it : arr) {
            ret.put(it, ret.getOrDefault(it, 0) + 1);
        }
        return ret;
    }

    public static void main(String[] args) {

        System.out.println(count(new String[]{"dd", "aa", "bb", "dd", "aa", "dd", "bb", "dd", "aa", "cc", "bb", "cc", "dd", "cc"}));


//        System.exit(0);

        for (int i = 0; ; i++) {
            int N = 6;

            String[] arr = new String[N];

            for (int j = 0; j < N; j++) {
                arr[j] = LeetUtils.randomString(2, false);
                if (LeetUtils.random.nextBoolean()){
                    StringBuilder builder = new StringBuilder(arr[j]);
                    builder.setCharAt(1, builder.charAt(0));
                    arr[j] = builder.toString();
                }
            }

            CopyableIterator<String[]> iterator = new CopyableIterator<>(arr, strings -> Arrays.copyOfRange(strings, 0, strings.length));

            if (longestPalindrome(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringsToLeetCode(iterator.next()));
                System.out.println("MyAns : " + longestPalindrome(iterator.next()));
                System.out.println("Right : " + right(iterator.next()));
                return;
            }

        }

        // System.out.println("Ok");
    }

    public static int longestPalindrome(String[] words) {

        // （1）当两个元素之间处于相反字符串时 n+4
        // （2）当存在两个字符相等的字符串时 n+2 注：当存在多个两个字符相等的字符串时 也只加一次

        int N = words.length;

        Map<Integer, Integer> map = new HashMap<>(N);

        for (String word : words) {
            int num = getNum(word);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;

        int maxOdd = 0;

        for (String word : words) {
            int num = getNum(word);
            Integer c1 = map.remove(num);
            if (c1 == null) {
                continue;
            }
            if (word.charAt(0) == word.charAt(1)) {
                if (c1 % 2 == 0) {
                    ans += c1 << 1;
                } else {
                    maxOdd = Math.max(maxOdd, c1);
                }
            } else {
                Integer c2 = map.remove(getReverse(num));
                if (c2 == null) {
                    c2 = 0;
                }
                ans += Math.min(c1, c2) << 2;
            }
        }

        return ans + (maxOdd << 1);
    }

    public static int getNum(String word) {
        int c1 = word.charAt(0) - 97 + 1;
        int c2 = word.charAt(1) - 97 + 1;
        return (c1 << 15) | c2;
    }

    public static int getReverse(int num) {
        return ((num >> 15) & 0xff) | ((num & 0xff) << 15);
    }

    public static int right(String[] words) {
        char[][] ch = new char[26][26];
        int c1, c2;
        int l = 0;
        for (String w : words) {
            c1 = w.charAt(0) - 'a';
            c2 = w.charAt(1) - 'a';
            if (ch[c2][c1] > 0) {
                ch[c2][c1]--;
                l += 4;
                continue;
            }
            ch[c1][c2]++;
        }

        for (int i = 0; i < 26; i++) {
            if (ch[i][i] > 0) {
                l += 2;
                break;
            }
        }

        return l;
    }

}
