package com.zzzj.contest.dweek109;


import java.util.Arrays;

public class Leet6929 {

    public static void main(String[] args) {

        System.out.println(sortVowels("lEetcOde"));

        System.out.println(sortVowels("lYmpH"));

        System.out.println(sortVowels("uZcPmqAd"));

    }

    public static String sortVowels(String s) {

        int[] vowel = new int[129];

        Arrays.fill(vowel, -1);

        vowel['a'] = 5;
        vowel['e'] = 6;
        vowel['i'] = 7;
        vowel['o'] = 8;
        vowel['u'] = 9;

        vowel['A'] = 0;
        vowel['E'] = 1;
        vowel['I'] = 2;
        vowel['O'] = 3;
        vowel['U'] = 4;

        int[] cnt = new int[10];

        char[] chars = new char[10];
        chars[0] = 'A';
        chars[1] = 'E';
        chars[2] = 'I';
        chars[3] = 'O';
        chars[4] = 'U';

        chars[5] = 'a';
        chars[6] = 'e';
        chars[7] = 'i';
        chars[8] = 'o';
        chars[9] = 'u';

        StringBuilder builder = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            int index = vowel[c];

            if (index >= 0) {
                builder.append("?");
                cnt[index]++;
            } else {
                builder.append(c);
            }

        }

        for (int i = 0; i < s.length(); i++) {
            if (builder.charAt(i) == '?') {

                for (int j = 0; j < 10; j++) {

                    if (cnt[j] > 0) {
                        builder.setCharAt(i, chars[j]);
                        cnt[j]--;
                        break;
                    }

                }

            }
        }

        return builder.toString();
    }

    public static boolean isVowel(char c) {
        return false;
    }

}
