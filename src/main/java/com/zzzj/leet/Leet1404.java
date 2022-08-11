package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-04 23:07
 */
public class Leet1404 {


    public static void main(String[] args) {
        System.out.println(numSteps("111100001"));
    }

    public static int numSteps(String s) {
        int N = s.length();

        int ans = 0;

        char[] chars = s.toCharArray();

        for (int i = N - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                // 前面的1全部进位
                while (i >= 0 && chars[i] == '1') {
                    ans++;
                    i--;
                }
                if (i < 0) {
                    break;
                }
                chars[i] = '1';
                i++;
            } else {
                ans++;
            }
        }

        return ans;
    }

}
