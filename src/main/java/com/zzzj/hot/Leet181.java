package com.zzzj.hot;

import com.zzzj.util.Unresolved;

/**
 * @author Zzzj
 * @create 2022-04-17 19:57
 */
@Unresolved
public class Leet181 {


    public static void main(String[] args) {
        System.out.println(convertToTitle(28));
        System.out.println(convertToTitle(701));
        System.out.println(convertToTitle(2147483647));
    }

    public static final char[] TABLE = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String convertToTitle(int columnNumber) {
        StringBuilder builder = new StringBuilder();

        while (columnNumber > 0) {
            if (columnNumber >= 26) {
                int div = columnNumber / 26;
                builder.append(TABLE[div]);
                columnNumber -= div * 26;
            } else {
                int remain = columnNumber % 26;
                builder.append(TABLE[remain]);
                break;
            }
        }

        return builder.toString();
    }

}
