package com.zzzj.contest.week357;

public class Leet6925 {

    public static void main(String[] args) {

        System.out.println(finalString("string"));

        System.out.println(finalString("poiinter"));

    }

    public static String finalString(String s) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'i') builder.reverse();
            else builder.append(s.charAt(i));
        }

        return builder.toString();
    }

}
