package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-09-25 18:13
 */
public class Leet991 {

    public static int brokenCalc(int startValue, int target) {

        int ans = 0;

        while (startValue < target) {
            if (target % 2 == 0) {
                target /= 2;
                ans++;
            } else {
                target = (target + 1) / 2;
                ans += 2;
            }
        }

        ans += startValue - target;
        return ans;
    }


}
