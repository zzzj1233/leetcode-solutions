package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author zzzj
 * @create 2021-10-25 10:08
 */
public class LeetUtils {

    public static Random random = new Random();

    public static int[] newArray(int n) {
        return newArray(n, 10);
    }

    public static int[] newArray(int n, int m) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = random.nextInt(m) + 1;
        }
        return result;
    }

    public static String newString(int n, int max) {
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            result[i] = (char) (random.nextInt(max) + 97);
        }
        return String.valueOf(result);
    }

    public static String newString(int n) {
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            result[i] = (char) (random.nextInt(26) + 97);
        }
        return String.valueOf(result);
    }


    public static String randomTreeStr(int n) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && random.nextInt(10) == 9) {
                list.add("null");
            } else {
                list.add(String.valueOf(random.nextInt(100)));
            }
        }

        String str = String.join(",", list);

        return "[" + str + "]";
    }

    public static TreeNode randomTree(int n) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && random.nextInt(10) == 9) {
                list.add("null");
            } else {
                list.add(String.valueOf(random.nextInt(100)));
            }
        }

        String str = String.join(",", list);

        return TreeNode.buildTree("[" + str + "]");
    }

    public static String randomFullTree() {
        ArrayList<String> list = new ArrayList<>();

        int N = random.nextInt(100);

        for (int i = 0; i < N; i++) {
            list.add(String.valueOf(random.nextInt(100)));
        }

        String str = String.join(",", list);

        return "[" + str + "]";
    }


    public static String randomNumberString(int length) {
        String str = "0123456789";
        return randomString0(str, length);
    }

    // 是否包含大写字母
    public static String randomString(int length, boolean containsUpper) {
        String str;

        if (containsUpper) {
            str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        } else {
            str = "abcdefghijklmnopqrstuvwxyz";
        }


        return randomString0(str, length);
    }

    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return randomString0(str, length);
    }

    public static String randomString0(String candidate, int length) {
        Random random = new Random();

        StringBuffer sb = new StringBuffer(length);

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(candidate.length());

            sb.append(candidate.charAt(number));
        }

        return sb.toString();
    }

    public static int[] randomBinaryArr(int n) {

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            if (random.nextInt() % 2 != 0) {
                result[i] = 1;
            }
        }

        return result;
    }

    public static char[][] random2DChars(int M, int N, boolean containsUpper) {
        char[][] result = new char[M][N];

        String candidate;

        if (containsUpper) {
            candidate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        } else {
            candidate = "abcdefghijklmnopqrstuvwxyz";
        }

        for (int i = 0; i < result.length; i++) {

            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = candidate.charAt(random.nextInt(candidate.length()));
            }

        }

        return result;
    }

    // source e.g : [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
    public static char[][] convertChars(String source) {

        source = source.substring(1, source.length() - 1);

        String[] split = source.split("],?");

        char[][] result = new char[split.length][];

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].substring(1);
            String[] chars = oneD.split(",");
            // 去除双引号
            result[i] = new char[chars.length];

            for (int j = 0; j < chars.length; j++) {
                String singleChar = chars[j].replaceAll("\"", "");
                result[i][j] = singleChar.charAt(0);
            }

        }

        return result;
    }

}