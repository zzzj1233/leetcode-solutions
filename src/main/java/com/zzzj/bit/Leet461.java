package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-08-02 16:04
 */
public class Leet461 {

    public static int hammingDistance(int x, int y) {

        int ans = 0;

        for (int i = 0; i < 31; i++) {
            if (((x >> i) & 1) != ((y >> i) & 1)) {
                ans++;
            }
        }

        return ans;
    }

}
