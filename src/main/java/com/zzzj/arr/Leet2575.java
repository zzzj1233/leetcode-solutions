package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-08-04 16:57
 */
public class Leet2575 {


    public static int[] divisibilityArray(String word, int m) {

        int N = word.length();

        int[] ans = new int[N];

        // ( A + B ) % C = ( ( A % C ) + ( B % C ) ) % C

        long value = 0;

        for (int i = 0; i < N; i++) {

            value = value * 10 + Character.digit(word.charAt(i), 10);

            ans[i] = value % m == 0 ? 1 : 0;

            value %= m;
        }

        return ans;
    }

}
