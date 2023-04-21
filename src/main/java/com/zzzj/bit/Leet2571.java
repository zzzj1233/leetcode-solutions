package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2023-04-13 16:00
 */
public class Leet2571 {

    public static void main(String[] args) {
        System.out.println(minOperations(39));
        System.out.println(minOperations(54));
    }

    // 111011101
    public static int minOperations(int n) {

        int ans = 0;

        // ?0
        // 01
        // 11

        while (n > 0) {
            if ((n & 1) == 0) {
                n >>= 1;
            } else if ((n & 0b11) == 0b11) {
                ans++;
                n >>= 2;
                n++;
            } else {
                ans++;
                n >>= 1;
            }
        }


        return ans;
    }

}
