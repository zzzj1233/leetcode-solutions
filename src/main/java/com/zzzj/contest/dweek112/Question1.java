package com.zzzj.contest.dweek112;

public class Question1 {

    public static void main(String[] args) {

        // "bnxw"
        //"bwxn"
        System.out.println(canBeEqual("bnxw", "bwxn"));

    }

    public static boolean canBeEqual(String s1, String s2) {

        // 长度都为4
        if (s1.equals(s2))
            return true;

        int N = s1.length();

        int idx = -1;

        if (s1.charAt(0) != s2.charAt(0) || s1.charAt(2) != s2.charAt(2))
            if (s1.charAt(2) != s2.charAt(0) || s2.charAt(2) != s1.charAt(0))
                return false;

        if (s1.charAt(1) != s2.charAt(1) || s1.charAt(3) != s2.charAt(3))
            if (s1.charAt(1) != s2.charAt(3) || s2.charAt(1) != s1.charAt(3))
                return false;

        return true;
    }

}
