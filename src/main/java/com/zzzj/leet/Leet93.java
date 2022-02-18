package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-10-16 23:40
 */
public class Leet93 {

    private List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Leet93 leet93 = new Leet93();

        System.out.println(leet93.restoreIpAddresses("000256"));
    }

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }

        restore(s, 0, 9, new StringBuilder());

        return result;
    }

    public void restore(String s, int ignore, int minLength, StringBuilder stringBuilder) {

        for (int i = 0; i < 3; i++) {
            if (s.length() - (i + 1) - ignore <= minLength) {
                if (ignore + i >= s.length()) {
                    return;
                }

                int digit = Character.digit(s.charAt(i + ignore), 10);

                if (i > 0 && Character.digit(s.charAt(ignore), 10) == 0) {
                    return;
                }

                if (i == 2 && Integer.parseInt(s.substring(ignore, ignore + i + 1)) > 255) {
                    continue;
                }

                StringBuilder copy = new StringBuilder(stringBuilder);

                for (int j = 0; j <= i; j++) {
                    copy.append(s.charAt(j + ignore));
                }

                if (minLength == 0) {
                    result.add(copy.toString());
                } else {
                    copy.append('.');
                    restore(s, ignore + i + 1, minLength - 3, copy);
                }
            }
        }


    }

}
