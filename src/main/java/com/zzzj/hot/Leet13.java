package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-01-17 10:54
 */
public class Leet13 {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("III"));
    }

    public static int romanToInt(String s) {
        int[] table = new int[126];

        table['I'] = 1;
        table['V'] = 5;
        table['X'] = 10;
        table['L'] = 50;
        table['C'] = 100;
        table['D'] = 500;
        table['M'] = 1000;

        int ans = 0;

        int prev = 0;

        for (int i = 0; i < s.length(); i++) {
            int cur = table[s.charAt(i)];
            if (prev < cur) {
                ans -= prev << 1;
            }
            ans += cur;
            prev = cur;
        }

        return ans;
    }

}
