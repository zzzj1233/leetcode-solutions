package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2023-04-12 16:41
 */
public class Leet2011 {

    public static int finalValueAfterOperations(String[] operations) {

        int value = 0;

        for (String operation : operations) {
            char first = operation.charAt(0);
            char last = operation.charAt(operation.length() - 1);

            if (first == '+' || last == '+') {
                value++;
            } else {
                value--;
            }

        }

        return value;
    }

}
