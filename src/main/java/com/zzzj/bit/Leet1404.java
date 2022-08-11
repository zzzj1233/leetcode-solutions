package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-08-05 15:09
 */
public class Leet1404 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString(10, "01");
            StringBuilder builder = new StringBuilder(str);
            builder.setCharAt(0, '1');
            str = builder.toString();
            if (numSteps(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int numSteps(String s) {
        int N = s.length();

        int ans = 0;

        int nextOneIndex = -1;

        for (int i = N - 1; i >= 0; ) {
            while (i >= 0 && i != nextOneIndex && s.charAt(i) == '0') {
                i--;
                ans++;
            }
            if (i == 0) {
                if (s.charAt(0) == '0') {
                    return ans + 1;
                } else {
                    return ans;
                }
            }
            // i == 1
            ans += 2;
            i--;
            while (i >= 0 && s.charAt(i) == '1') {
                i--;
                ans++;
            }
            nextOneIndex = i;
        }

        return ans;
    }

    public static int right(String s) {
        int zero = 0;
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) != '1') i--;
        if (i == 0) return s.length() - 1;
        while (i > 0) if (s.charAt(i--) == '0') zero++;
        return zero + s.length() + 1;
    }

}
