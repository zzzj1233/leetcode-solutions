package com.zzzj.leet;

public class Leet38 {

    public static void main(String[] args) {

        System.out.println(countAndSay(5));
        System.out.println(countAndSay(6));
        System.out.println(countAndSay(7));

    }

    public static String countAndSay(int n) {

        if (n == 1)
            return "1";

        String word = countAndSay(n - 1);

        StringBuilder builder = new StringBuilder();

        int cnt = 1;

        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);

            if (i + 1 < word.length() && word.charAt(i + 1) == c)
                cnt++;
            else {
                builder.append(cnt).append(c);
                cnt = 1;
            }

        }

        return builder.toString();
    }

}
