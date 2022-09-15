package com.zzzj.leet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-09-08 11:40
 */
public class Leet1451 {


    public static void main(String[] args) {
        System.out.println(arrangeWords("Keep calm and code on"));
    }

    public static String arrangeWords(String text) {
        String[] words = text.split(" ");

        words[0] = Character.toLowerCase(words[0].charAt(0)) + words[0].substring(1);

        Arrays.sort(words, Comparator.comparingInt(String::length));

        words[0] = Character.toUpperCase(words[0].charAt(0)) + words[0].substring(1);

        return String.join(" ", words);
    }


}
