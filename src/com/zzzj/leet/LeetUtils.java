package com.zzzj.leet;

import java.util.ArrayList;
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


    // 是否包含大写字母
    public static String randomString(int length, boolean containsUpper) {
        String str;

        if (containsUpper) {
            str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        } else {
            str = "abcdefghijklmnopqrstuvwxyz";
        }

        StringBuffer sb = new StringBuffer(length);

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(containsUpper ? 52 : 26);

            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random = new Random();

        StringBuffer sb = new StringBuffer(length);

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(52);

            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

}
