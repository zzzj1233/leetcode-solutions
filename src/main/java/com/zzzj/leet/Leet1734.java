package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-06 18:14
 */
public class Leet1734 {


    public static int[] decode(int[] encoded) {

        int N = encoded.length;

        int xor = 0;

        for (int i = 1; i <= N + 1; i++) {
            xor ^= i;
        }

        // 假设 N = 4 , 原数组长度一定是奇数

        // xor = 0 ^ 1 ^ 2 ^ 3 ^ 4 ^ 5

        // encoded[1] =  1 ^ 2

        // encoded[3] =  3 ^ 4

        // xor ^ encoded[1] ^ encoded[3] = origin[0]

        for (int i = 1; i < N; i += 2) {
            xor ^= encoded[i];
        }

        int[] ans = new int[N + 1];

        ans[0] = xor;

        for (int i = 1; i <= N; i++) {
            ans[i] = encoded[i - 1] ^ ans[i - 1];
        }

        return ans;
    }

}
