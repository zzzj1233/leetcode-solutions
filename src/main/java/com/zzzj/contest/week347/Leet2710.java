package com.zzzj.contest.week347;

public class Leet2710 {

    public static void main(String[] args) {
        System.out.println(removeTrailingZeros("51230100"));
        System.out.println(removeTrailingZeros("123"));
    }

    public static String removeTrailingZeros(String num) {

        int lastIndex = num.length() - 1;

        while (lastIndex >= 0 && num.charAt(lastIndex) == '0') {
            lastIndex--;
        }

        if (lastIndex < 0) return "";
        return num.substring(0, lastIndex + 1);
    }

}
