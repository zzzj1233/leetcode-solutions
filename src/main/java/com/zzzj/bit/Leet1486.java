package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2023-05-12 16:02
 */
public class Leet1486 {

    public static int xorOperation(int n, int start) {

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans ^= (i << 1) + start;
        }

        return ans;
    }

}
