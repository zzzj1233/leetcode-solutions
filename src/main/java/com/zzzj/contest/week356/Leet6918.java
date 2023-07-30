package com.zzzj.contest.week356;

import javax.swing.plaf.MenuBarUI;

public class Leet6918 {

    public static void main(String[] args) {

        System.out.println(minimumString("abc", "bca", "aaa"));

        System.out.println(minimumString("ab", "ba", "aba"));

        System.out.println(minimumString("ca", "a", "a"));

        System.out.println(minimumString("a", "c", "ca"));

    }

    public static String minimumString(String a, String b, String c) {


        String case1 = violence(violence(a, b), c);
        String case2 = violence(violence(a, c), b);
        String case3 = violence(violence(b, a), c);
        String case4 = violence(violence(b, c), a);
        String case5 = violence(violence(c, a), b);
        String case6 = violence(violence(c, b), a);

        return findMin(case1, case2, case3, case4, case5, case6);
    }

    public static String findMin(String... strs) {
        String minimum = null;

        for (String str : strs) {
            if (minimum == null || (str.length() <= minimum.length() && str.compareTo(minimum) < 0) || str.length() < minimum.length()) minimum = str;
        }

        return minimum;
    }

    public static String violence(String a, String b) {

        StringBuilder builder = new StringBuilder(a.length() + b.length());

        builder.append(a);

        int j = 0;
        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) == b.charAt(j)) {
                int k = i;
                int x = j;

                boolean ok = true;

                while (k < a.length() && x < b.length()) {
                    if (a.charAt(k) != b.charAt(x)) {
                        ok = false;
                        break;
                    }
                    k++;
                    x++;
                }

                if (k == a.length()) {
                    while (x < b.length()) {
                        builder.append(b.charAt(x));
                        x++;
                    }
                    return builder.toString();
                } else if (x == b.length()) {
                    return builder.toString();
                }
            }

        }

        return builder.append(b).toString();
    }


}
