package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-07 16:12
 */
public class Leet186 {

    public static void main(String[] args) {
        reverseWords("the sky is blue".toCharArray());
    }

    public static void reverseWords(char[] str) {
        reverse(str, 0, str.length - 1);

        int start = -1;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                if (start != -1) {
                    reverse(str, start, i - 1);
                    start = -1;
                }
            } else {
                if (start == -1) {
                    start = i;
                }
            }
        }

        if (start != -1) {
            reverse(str, start, str.length - 1);
        }

    }

    public static void reverse(char[] str, int i, int j) {
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }

}
