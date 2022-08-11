package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-06 19:40
 */
public class Leet1256 {

    public static String encode(int num) {
        // 抹除最高位的1,再+1
        return String.valueOf(num + 1).substring(1);
    }

}
