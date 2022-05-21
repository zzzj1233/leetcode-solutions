package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-20 18:32
 */
public class Leet520 {

    public static void main(String[] args) {

    }

    public static boolean detectCapitalUse(String word) {

        boolean firstUp = Character.isUpperCase(word.charAt(0));
        boolean allUp = true;
        boolean allLow = true;

        for (int i = 1; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                allLow = false;
            } else {
                allUp = false;
            }
        }

        return (firstUp && word.length() > 1 && allLow) || (allLow && !firstUp) || (allUp && firstUp);
    }

}
