package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-19 15:28
 */
public class Leet443 {

    public static void main(String[] args) {
        char[] chars = "aaabbaa".toCharArray();
        System.out.println(compress(chars));
        System.out.println(chars);
    }

    // ["a","a","a","b","b","a","a"]
    // ["a","3","a","b","2","a"]
    // ["a","3","b","2","a","2"]
    public static int compress(char[] chars) {
        char c = chars[0];

        int i = 0;

        int j = 0;

        int repeat = 0;

        while (j < chars.length) {
            while (j < chars.length && chars[j] == c) {
                j++;
                repeat++;
            }

            chars[i++] = c;

            if (repeat > 1) {
                String rt = String.valueOf(repeat);
                for (int k = 0; k < rt.length(); k++) {
                    chars[i++] = rt.charAt(k);
                }
            }

            repeat = 0;

            if (j < chars.length) {
                c = chars[j];
            }
        }

        return chars.length - (chars.length - i);
    }

}
