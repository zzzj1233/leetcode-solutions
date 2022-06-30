package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-27 12:13
 */
public class Leet1323 {

    public static int maximum69Number(int num) {
        String numStr = String.valueOf(num);

        char[] chars = numStr.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }

        return Integer.parseInt(String.valueOf(chars));
    }

}
