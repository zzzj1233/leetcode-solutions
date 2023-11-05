package com.zzzj.arr;

import java.time.temporal.ValueRange;

/**
 * @author zzzj
 * @create 2021-12-21 18:47
 */
public class Leet165 {

    public static void main(String[] args) {
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
    }

    public static int compareVersion(String version1, String version2) {
        String v1 = version1.replaceAll("[.0]", "");
        String v2 = version2.replaceAll("[.0]", "");

        int end = Math.min(v1.length(), v2.length());

        for (int i = 0; i < end; i++) {
            int c1 = Character.digit(v1.charAt(i), 10);
            int c2 = Character.digit(v2.charAt(i), 10);
            if (c1 > c2) {
                return 1;
            } else if (c2 > c1) {
                return -1;
            }
        }

        if (v1.length() == v2.length()) {
            return 0;
        }
        if (v1.length() > v2.length()) {
            return 1;
        }
        return -1;
    }

}
