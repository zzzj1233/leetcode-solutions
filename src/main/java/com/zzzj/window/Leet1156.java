package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-01-21 10:22
 */
public class Leet1156 {

    public static void main(String[] args) {
        System.out.println(maxRepOpt1("ababa"));
        System.out.println(maxRepOpt1("aaabaaa"));
        System.out.println(maxRepOpt1("aaabbaaa"));
        System.out.println(maxRepOpt1("aaaaa"));
        System.out.println(maxRepOpt1("abcdef"));

        final String str = LeetUtils.newString(100, 3);

        System.out.println("\"" + str + "\"");
        System.out.println(maxRepOpt1(str));

        //        System.out.println(maxRepOpt1("bbdaebaade"));
    }

    public static int maxRepOpt1(String text) {
        int[] table = new int[26];

        return -1;
    }

}
