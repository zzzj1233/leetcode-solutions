package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-17 20:18
 */
public class Leet191 {

    public static void main(String[] args) {
        System.out.println(hammingWeight(Integer.parseInt("00000000000000000000000000001011", 2)));
        System.out.println(hammingWeight(Integer.parseInt("00000000000000000000000010000000", 2)));
        System.out.println(hammingWeight(Integer.parseInt("11111111111111111111111111111101", 2)));
    }


    public static int hammingWeight(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) {
                ans++;
            }
        }

        return ans;
    }

}
