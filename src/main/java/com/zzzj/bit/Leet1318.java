package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-08-02 16:00
 */
public class Leet1318 {

    public static int minFlips(int a, int b, int c) {
        int ans = 0;

        for (int i = 0; i < 31; i++) {
            // 这一位是1
            if (((c >> i) & 1) == 1) {
                if (((a >> i) & 1) == 0 && ((b >> i) & 1) == 0) {
                    ans++;
                }
            } else {
                ans += ((a >> i) & 1);
                ans += ((b >> i) & 1);
            }
        }

        return ans;
    }

}
