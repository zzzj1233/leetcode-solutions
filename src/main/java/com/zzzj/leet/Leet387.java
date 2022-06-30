package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-27 12:23
 */
public class Leet387 {

    public static int firstUniqChar(String s) {
        int[] count = new int[128];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;
    }

}
