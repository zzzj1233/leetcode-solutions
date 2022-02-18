package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-01-17 10:40
 */
public class Leet12 {

    public static void main(String[] args) {
        System.out.println(intToRoman(2555));
    }

    /**
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public static String intToRoman(int num) {
        String[][] chars = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };

        StringBuilder ans = new StringBuilder(30);

        ans.append(chars[3][num / 1000 % 10]);
        ans.append(chars[2][num / 100 % 10]);
        ans.append(chars[1][num / 10 % 10]);
        ans.append(chars[0][num % 10]);

        return ans.toString();
    }

}
